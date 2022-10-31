package ru.spartak.gard.ui.root_screen.main_screen.home_tab.levels_screen

import ru.spartak.gard.R

sealed class Level(
    val name: String,
    val nextName:String,
    val unlockPtsFirst: Int,
    val unlockPtsSecond: Int,
    val imageId: Int,
    val startingBonus: Int,
    val tasksBonus: Int
) {
    object Newbie : Level(
        name = "Newbie",
        nextName = "Skilled",
        unlockPtsFirst = 0,
        unlockPtsSecond = 5000,
        imageId = R.drawable.newbie_image,
        startingBonus = 0,
        tasksBonus = 0,
    )
    object Skilled : Level(
        name = "Skilled",
        nextName = "Expert",
        unlockPtsFirst = 5000,
        unlockPtsSecond = 10000,
        imageId = R.drawable.newbie_image,
        startingBonus = 200,
        tasksBonus = 10,
    )
    object Expert : Level(
        name = "Expert",
        nextName="Ninja",
        unlockPtsFirst = 10000,
        unlockPtsSecond = 30000,
        imageId = R.drawable.newbie_image,
        startingBonus = 400,
        tasksBonus = 20,
    )
    object Ninja : Level(
        name = "Ninja",
        nextName = "Master",
        unlockPtsFirst = 30000,
        unlockPtsSecond = 60000,
        imageId = R.drawable.newbie_image,
        startingBonus = 750,
        tasksBonus = 30,
    )
    object Master : Level(
        name = "Master",
        nextName = "Grandmaster",
        unlockPtsFirst = 60000,
        unlockPtsSecond = 80000,
        imageId = R.drawable.newbie_image,
        startingBonus = 1000,
        tasksBonus = 40,
    )
    object Grandmaster : Level(
        name = "Grandmaster",
        nextName = "",
        unlockPtsFirst = 80000,
        unlockPtsSecond = Int.MAX_VALUE,
        imageId = R.drawable.newbie_image,
        startingBonus = 2000,
        tasksBonus = 50,
    )
}