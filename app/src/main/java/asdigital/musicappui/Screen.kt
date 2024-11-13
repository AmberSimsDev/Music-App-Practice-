package asdigital.musicappui
//This class file contains all the different app screens
import androidx.annotation.DrawableRes

//REMEMBER: Sealed classes cannot be inherited from
sealed class Screen(val title: String, val route:String ) {
    //Shows the title to be displayed as well as route

    sealed class BottomScreen(
        //Displays the: Title, Route, Resource. Also inherits from Screen taking the title and route as parameters
        val bTitle: String, val bRoute: String, @DrawableRes val icon:Int):Screen(bTitle, bRoute){
        object Home: BottomScreen ("Home","home", R.drawable.baseline_music_note_24)

        object Library:BottomScreen("Library","library", R.drawable.ic_subscribe)

        object Browse: BottomScreen("Browse","browse", R.drawable.baseline_article_24)


    }



    sealed class  DrawerScreen(val dTitle:String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
            object Account: DrawerScreen(
                "Account",
                "account",
                R.drawable.ic_account
            )
        object Subscription: DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.ic_subscribe
        )
        object AddAccount: DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
        }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)