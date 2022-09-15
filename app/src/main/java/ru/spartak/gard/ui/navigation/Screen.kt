package ru.spartak.gard.ui.navigation

import ru.spartak.gard.R

sealed class Screen(val route: String, val title: String, val icon: Int) {
    object HomeScreen : Screen(route = "home_screen", title = "Home", icon = R.drawable.ic_home_icon)
    object GamesScreen : Screen(route = "games_screen", title = "Games", icon = R.drawable.ic_games_icon)
    object TasksScreen : Screen(route = "tasks_screen", title = "Tasks", icon = R.drawable.ic_tasks_icon)
    object ShopScreen : Screen(route = "shop_screen", title = "Shop", icon = R.drawable.ic_shop_icon)
}