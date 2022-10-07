package ru.spartak.gard.ui.main_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.ui.navigation.BottomScreen
import ru.spartak.gard.ui.navigation.NavGraph
import ru.spartak.gard.ui.theme.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomScreens = listOf(
        BottomScreen.HomeScreen,
        BottomScreen.GamesScreen,
        BottomScreen.TasksScreen,
        BottomScreen.ShopScreen,
    )
    GardTheme {
        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomScreens = bottomScreens
                )
            }
        ) {
            NavGraph(
                navController = navController,
                startDestination = BottomScreen.HomeScreen.route,
                modifier = Modifier.padding(bottom = it.calculateBottomPadding())
            )
        }
    }
}


@Composable
fun BottomBar(
    navController: NavHostController,
    bottomScreens: List<BottomScreen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
        modifier = Modifier.height(66.dp)
    ) {
        bottomScreens.forEach { screen ->
            AddItem(
                bottomScreen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }
}

@Composable
fun RowScope.AddItem(
    bottomScreen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.route == bottomScreen.route
    val stateColor by animateColorAsState(targetValue = if (selected) White else Dark300)
    BottomNavigationItem(
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = bottomScreen.icon),
                    contentDescription = "Navigation Icon",
                    tint = stateColor
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    text = bottomScreen.title,
                    style = MaterialTheme.typography.caption,
                    color = stateColor
                )
            }
        },
        selected = selected,
//        selected = currentDestination?.hierarchy?.any {
//            it.route == bottomScreen.route
//        } == true,
        onClick = { navController.navigate(bottomScreen.route) },
//        unselectedContentColor = Dark300,
//        selectedContentColor = White,
    )
}

