package ru.spartak.gard.ui.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.ui.navigation.NavGraph
import ru.spartak.gard.ui.navigation.Screen
import ru.spartak.gard.ui.theme.Dark300
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.White

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.HomeScreen,
        Screen.GamesScreen,
        Screen.TasksScreen,
        Screen.ShopScreen,
    )
    GardTheme {
        Scaffold(
            bottomBar = {
                BottomBar(
                    navController = navController,
                    screens = screens
                )
            }
        ) {
            NavGraph(
                navController = navController,
                startDestination = Screen.HomeScreen.route
            )
        }
    }
}


@Composable
fun BottomBar(
    navController: NavHostController,
    screens: List<Screen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
        modifier = Modifier.height(88.dp)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "Navigation Icon"
                )
                PaddingValues(horizontal = 8.dp)
                Text(text = screen.title)
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = { navController.navigate(screen.route) },
        unselectedContentColor = Dark300,
        selectedContentColor = White,
    )
}

