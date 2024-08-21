package dev.peppo.eventapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.peppo.eventapp.R
import dev.peppo.eventapp.navigation.Screen
import dev.peppo.eventapp.ui.screen.about.AboutScreen
import dev.peppo.eventapp.ui.screen.detail.DetailScreen
import dev.peppo.eventapp.ui.screen.home.HomeScreen

@Composable
fun EventApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Screen.DetailEvent.route) {
                MyTopBar(
                    navController
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { eventId ->
                        navController.navigate(Screen.DetailEvent.createRoute(eventId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailEvent.route,
                arguments = listOf(navArgument("eventId") { type = NavType.IntType })
            ) {
                val eventId = it.arguments?.getInt("eventId") ?: -1
                DetailScreen(
                    eventId = eventId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            if (currentRoute != Screen.About.route) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.testTag("profile")
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.profile_button),
                    )
                }
            }
        }
    )
}