package asdigital.musicappui
//Holds the state of the screen which we are at
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //We just want it to hold our current screen
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.AddAccount)

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }

//Again, it holds the state of the screen which we are at.
// Are you on the main screen?
//Are we on the account screen
}