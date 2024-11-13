package asdigital.musicappui.ui.theme
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.GridCells
import asdigital.musicappui.R

@Composable
fun Browse(){
    val categories = listOf("Hits", "Workout", "Meditation","Night Time","Commute","Dancing")
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories){ cat->
            BrowserItem(cat= cat, drawable = R.drawable.baseline_apps_24)
        }
    }
}
