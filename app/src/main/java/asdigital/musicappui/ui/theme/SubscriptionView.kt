package asdigital.musicappui.ui.theme


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable

fun SubscriptionView() {
    Column(
        modifier = Modifier.padding(top = 8.dp).height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {//What's inside the column
        Text("Manage Subscription",
            modifier = Modifier.padding(bottom = 8.dp))

        Card(modifier = Modifier.padding(end = 8.dp), elevation = 4.dp) {
            Column( modifier = Modifier.fillMaxSize().padding(16.dp)) { //Columns can only take vertical arrangement
                Text(text = "Musical")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Free Tier")
                    TextButton(onClick = {/*TODO*/ }) {
                        Row {
                            Text("See All Plans")
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Sell All Plans"
                            )
                        }
                    }
                }

                    Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                    //SECTION 2 : GET A PLAN
                    Row(modifier = Modifier.padding(vertical = 16.dp)) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Get a Plan"
                        )
                        Text(text = "Get a Plan")
                    }
                }
            }
        }
    }


