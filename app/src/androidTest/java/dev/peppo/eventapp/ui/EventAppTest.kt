package dev.peppo.eventapp.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.peppo.eventapp.assertCurrentRouteName
import dev.peppo.eventapp.navigation.Screen
import dev.peppo.eventapp.ui.theme.EventAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EventAppTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                EventApp(navController = navController)
            }
        }
    }

    @Test
    fun navhost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navhost_profile() {
        composeTestRule.onNodeWithTag("profile").performClick()
        navController.assertCurrentRouteName(Screen.About.route)
    }


}