package com.example.newstoday.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newstoday.languagescreen.LanguageScreen
import com.example.newstoday.profile.ProfileScreen
import com.example.newstoday.termsconditions.TermsConditionsScreen
import com.example.newstoday.screen.Screen


@Composable
fun Navigate() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.ProfileScreen.route) {
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                toLanguageScreen = { navController.navigate(Screen.Language.route)},
                toTermsConditionsScreen = {navController.navigate(Screen.TermsConditions.route)},
                signOut = {})
        }
        composable(Screen.TermsConditions.route) {
            TermsConditionsScreen(navController)
        }
        composable(Screen.Language.route) { LanguageScreen(navController) }
    }
}