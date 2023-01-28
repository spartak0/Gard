package ru.spartak.gard.ui.root_screen.main_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.spartak.gard.navigation.BottomScreen
import ru.spartak.gard.navigation.MainNavGraph
import ru.spartak.gard.ui.theme.Dark300
import ru.spartak.gard.ui.theme.GardTheme
import ru.spartak.gard.ui.theme.White
import ru.spartak.gard.ui.theme.spacing
import ru.spartak.gard.utils.Constant

@Composable
fun MainScreen(
    rootNavController: NavController,
    startDestination: String,
) {
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
            MainNavGraph(
                navController = navController,
                startDestination = startDestination,
                rootNavController = rootNavController,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(bottom = it.calculateBottomPadding())
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
                    //tint = if (selected) White else Dark300
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
        onClick = { navController.navigate(bottomScreen.route) },
    )
}

