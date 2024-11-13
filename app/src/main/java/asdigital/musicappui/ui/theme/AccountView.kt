package asdigital.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import asdigital.musicappui.R

@Composable
fun AccountView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) //Takes entire space to the top, all directions
    ){
        //MAIN ROW 1 w/ another row inside
        Row(
            modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
             //Takes the entire width on screen      //items are pushed apart from each other
        ){
            Row() {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Column {
                    Text("asdigital")
                    Text("@asdigitalassistant")
                }
            }
            IconButton(onClick = {}){//empty on click
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null)

           }
        }

        //MAIN ROW 2
        Row(modifier = Modifier.padding(top = 16.dp)){
            Icon(
                painter = painterResource(id = R.drawable.baseline_music_note_24),
                contentDescription ="My Music",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "My Music")
        }
        Divider()
    }
}