package dev.peppo.eventapp.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object DetailEvent: Screen("home/{eventId}") {
        fun createRoute(eventId: Int) = "home/$eventId"
    }
}