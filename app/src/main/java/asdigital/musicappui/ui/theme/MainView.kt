@file:OptIn(ExperimentalMaterial3Api::class)

package asdigital.musicappui.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import asdigital.musicappui.MainViewModel
import asdigital.musicappui.R
import asdigital.musicappui.Screen
import asdigital.musicappui.screensInBottom
import asdigital.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    //We need a scaffold State to take care of the state of the scaffold. It's the parent UI element
    //for the whole page containing the topbar, content, bottom bar and scafold
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val isSheetFullScreen by remember{ mutableStateOf(false)}
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    //Allow us to find out on which "View" we currently are
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember {
        mutableStateOf(false)  // to be updated
    }

    val currentScreen = remember{
        viewModel.currentScreen.value
    }

    val title = remember{
      mutableStateOf(currentScreen.title)
    }

    val modalSheetState = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, // by default its hidden
        confirmStateChange = {it != ModalBottomSheetValue.HalfExpanded}
        ) // Needs to know state of model sheet in order to update ui

    //To have arounded corners
    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    val bottomBar: @Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomNavigation(Modifier.wrapContentSize()){
                screensInBottom.forEach{  //Creating a bottom nav for each screen on list
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    Log.d("Navigation", "Item: ${item.bRoute}, Current Rout:$currentRoute. Is Selected")
                    val tint = if(isSelected)Color.White else Color.Black

                    BottomNavigationItem(selected = currentRoute == item.bRoute,
                                  onClick = {
                                      controller.navigate(item.bRoute)
                                      title.value = item.bTitle
                                            },icon = {

                                      Icon(tint= tint,
                                          contentDescription = item.bTitle, painter = painterResource(id = item.icon))
                                  },
                                       label = { Text(text = item.bTitle, color = tint)}
                                  ,selectedContentColor = Color.White,
                                  unselectedContentColor = Color.Black
                                  )

                    }
                }
            }
        }
        
        ModalBottomSheetLayout(
            sheetState = modalSheetState,
            sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
            sheetContent = {
           MoreBottomSheet(modifier = modifier)
        }){
            Scaffold(
                bottomBar = bottomBar,
                topBar = {
                    TopAppBar(title = { Text(title.value) },
                        actions = {//are the 3 dots on top right
                              IconButton(
                                  onClick = {
                                      scope.launch{
                                          if(modalSheetState.isVisible)
                                              modalSheetState.hide()
                                          else
                                              modalSheetState.show()
                                      }
                                  }
                              ) {
                                 Icon(imageVector = Icons.Default.MoreVert, contentDescription = null )
                              }
                        },
                        navigationIcon = {IconButton(onClick = {
                            //open the drawers
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                        }
                        }
                    )
                },scaffoldState = scaffoldState,
                drawerContent = {
                    LazyColumn(Modifier.padding(16.dp)){
                        items(screensInDrawer){
                                item ->
                            DrawerItem(selected = currentRoute == item.dRoute,  item = item) {
                                //Remember, selected is a boolean and we're saying its true if the current
                                //route is the item's .dRoute. Otherwise it's false
                                scope.launch{
                                    scaffoldState.drawerState.close()
                                }
                                if(item.dRoute == "add_account"){
                                    dialogOpen.value = true //opens dialog
                                }else{
                                    controller.navigate(item.dRoute)
                                    title.value = item.dTitle
                                }
                            }
                        }
                    }
                }

            ) { //make sure to use padding values consistantly else the program will complain
                Navigation(navController = controller, viewModel = viewModel, pd = it)

                AccountDialog(dialogOpen = dialogOpen)
            }
        }



}

@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                MaterialTheme.colors.primarySurface
            )
    ){
        Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween){
            Row(modifier = modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Settings")
                Text(text = "Settings", fontSize = 20.sp, color = Color.White)
           }
            Row(modifier = modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription = "Share")
                Text(text = "Share", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.baseline_help_outline_24),
                    contentDescription = "Help")
                Text(text = "Help", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}



@Composable
fun DrawerItem(
    selected:  Boolean, //to toggle on/off
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit //meaning you will give it a value later
){
 // Let's do the background
   val background = if (selected) Color.DarkGray else Color.White
    // When selected its going to be dark gray when not selected it's going to be white
 // Here we'll add a row
   Row(
       Modifier
           .fillMaxWidth()
           .padding(horizontal = 8.dp, vertical = 16.dp)
           .background(background)
           .clickable {
               onDrawerItemClicked() // will call the function(method) above
           }){
       androidx.compose.material.Icon(
         painter = painterResource(id = item.icon), //takes our "icon" from the Resource Manager
         contentDescription = item.dTitle,
         Modifier.padding(end = 8.dp, top = 4.dp)
     ) //Next to the icon is the next below
       Text(
         text = item.dTitle, //copies title gotten from the item
         style = MaterialTheme.typography.h5,
     )
  }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd:PaddingValues){

    NavHost(navController = navController as NavHostController,
            startDestination =Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd) ){

            composable(Screen.BottomScreen.Home.bRoute){
                Home()
            }

            composable(Screen.BottomScreen.Library.bRoute){
                Library()
            }

            composable(Screen.BottomScreen.Browse.bRoute){
            //TODO Add BROWSE SCREEN
                Browse()
            }
            composable(Screen.DrawerScreen.Account.route){
            // When one clicks on account I want the 'AccountView' to appear
                AccountView()
            }
            composable(Screen.DrawerScreen.Subscription.route){
                SubscriptionView()
            }
    }
    //you are here
}