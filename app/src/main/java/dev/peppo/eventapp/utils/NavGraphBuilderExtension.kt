package dev.peppo.eventapp.utils

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.peppo.eventapp.navigation.Screen

fun NavGraphBuilder.favourite(navigateToDetail: (Int) -> Unit) {
    composable(route = Screen.Favourite.route) {
        DFFavourite(navigateToDetail)
    }
}