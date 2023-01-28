package ru.spartak.gard.navigation

import ru.spartak.gard.R

sealed class BottomScreen(val route: String, val title: String, val icon: Int) {
    object HomeScreen : BottomScreen(route = "home_screen", title = "Home", icon = R.drawable.ic_home_icon)
    object GamesScreen : BottomScreen(route = "games_screen", title = "Games", icon = R.drawable.ic_games_icon)
    object TasksScreen : BottomScreen(route = "tasks_screen", title = "Tasks", icon = R.drawable.ic_tasks_icon)
    object ShopScreen : BottomScreen(route = "shop_screen", title = "Shop", icon = R.drawable.ic_shop_icon)
}