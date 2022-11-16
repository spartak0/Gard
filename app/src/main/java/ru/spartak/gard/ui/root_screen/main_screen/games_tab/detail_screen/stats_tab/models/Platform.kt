package ru.spartak.gard.ui.root_screen.main_screen.games_tab.detail_screen.stats_tab.models

import ru.spartak.gard.R

sealed class Platform(val name: String, val iconId: Int? = null) {
    object All : Platform(name = "All");
    object Mobile : Platform(name = "Mobile", iconId = R.drawable.ic_touchscreen)
    object Console : Platform(name = "Console", iconId = R.drawable.ic_console)
    object PC : Platform(name = "PC", iconId = R.drawable.ic_keyboard_and_mouse);

    override fun equals(other: Any?): Boolean = (other is Platform) && (other.name == this.name)
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (iconId ?: 0)
        return result
    }
}
