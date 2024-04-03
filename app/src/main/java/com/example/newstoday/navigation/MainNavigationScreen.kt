package com.example.newstoday.navigation


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.views.articlePage.ArticlePageScreen
import com.example.newstoday.views.booksmark.Bookmarks
import com.example.newstoday.views.categories.CategoriesScreen
import com.example.newstoday.views.mainScreen.MainScreen
import com.example.newstoday.views.onboard.OnboardingScreen
import com.example.newstoday.views.onboard.SplashScreen
import com.example.newstoday.views.profiles.languagescreen.LanguageScreen
import com.example.newstoday.views.profiles.profile.ProfileScreen
import com.example.newstoday.views.profiles.termsconditions.TermsConditionsScreen
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationScreen(viewModel: NewsViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
                    Screen.Home.route,
                    Screen.CategoriesScreen.route,
                    Screen.Bookmarks.route,
                    Screen.Profile.route
                )
            ) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Onboarding.route) {
            composable(Screen.Onboarding.route) {
                OnboardingScreen(Modifier.fillMaxSize(), navController)

            }
            composable(Screen.CategoriesScreen.route) {
                CategoriesScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 192.dp)
                        .background(Color.White),
                    navController,
                    viewModel
                )
            }
            composable(Screen.Home.route) {
                MainScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 168.dp)
                        .background(Color.White),
                    navController,
                    viewModel
                )
            }
            composable(Screen.Bookmarks.route) {
                Bookmarks(
                    Modifier
                        .fillMaxSize(),
                    navController,
                    viewModel
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    Modifier
                        .fillMaxSize(),
                    navController,
                    viewModel
                )
            }
            composable(Screen.Language.route) {
                LanguageScreen(
                    Modifier
                        .fillMaxSize(),
                    navController,
                    viewModel
                )
            }
            composable(Screen.TermsConditions.route) {
                TermsConditionsScreen(
                    Modifier
                        .fillMaxSize(),
                    navController,
                    viewModel
                )
            }
            composable(Screen.NewsScreen.route) {
                ArticlePageScreen(
                    Modifier
                        .fillMaxSize(),
                    navController,
                    viewModel
                )
            }
        }
    }
}