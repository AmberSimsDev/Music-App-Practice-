package asdigital.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries = listOf<Lib>(
    Lib(R.drawable.ic_subscribe,"Playlist"),
    Lib(R.drawable.ic_account,"Artists"),
    Lib(R.drawable.baseline_burst_mode_24,"Album"),
    Lib(R.drawable.baseline_music_note_24,"Songs"),
    Lib(R.drawable.baseline_headphones_24,"Genre")
)
