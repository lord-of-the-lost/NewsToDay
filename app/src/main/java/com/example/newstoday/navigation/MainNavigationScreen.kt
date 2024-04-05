package com.example.newstoday.navigation


//import com.example.newstoday.views.profiles.languagescreen.LanguageScreen
//import com.example.newstoday.views.profiles.profile.ProfileScreen
//import com.example.newstoday.views.profiles.termsconditions.TermsConditionsScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newstoday.R
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.views.articlePage.ArticlePageScreen
import com.example.newstoday.views.booksmark.Bookmarks
import com.example.newstoday.views.categories.CategoriesScreen
import com.example.newstoday.views.mainScreen.MainScreen
import com.example.newstoday.views.onboard.OnboardingScreen
import com.example.newstoday.views.profiles.languagescreen.LanguageScreenContent
import com.example.newstoday.views.profiles.profile.ProfileScreenContent
import com.example.newstoday.views.profiles.termsconditions.ScrollableText
import com.example.newstoday.views.topappbar.TopAppBarCust

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationScreen(viewModel: NewsViewModel) {
	val navController = rememberNavController()
	
	Scaffold(Modifier
		.background(Color.White),
		
		topBar = {
			if (navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
					Screen.Home.route,
					Screen.CategoriesScreen.route,
					Screen.Bookmarks.route,
					Screen.Profile.route,
					Screen.TermsConditions.route,
					Screen.Language.route
				)
			) {
				
				TopAppBarCust(
					screen = when (navController.currentBackStackEntryAsState().value?.destination?.route) {
						Screen.Home.route -> stringResource(id = R.string.browse)
						Screen.CategoriesScreen.route -> stringResource(id = R.string.select_favorite)
						Screen.Bookmarks.route -> stringResource(id = R.string.bookmarks)
						Screen.Profile.route -> stringResource(id = R.string.profile)
						Screen.TermsConditions.route -> stringResource(id = R.string.terms_conditions)
						Screen.Language.route -> stringResource(id = R.string.language_language)
						else -> ""
					},
					navController,
					Modifier
						.background(Color.White)
				)
			}
		},
		
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
	) { innerPadding ->
		Column(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
				.background(Color.White)
		) {
			NavHost(navController, startDestination = Screen.Onboarding.route) {
				composable(Screen.Onboarding.route) {
					OnboardingScreen(Modifier.fillMaxSize(), navController)
				}
				composable(Screen.CategoriesScreen.route) {
					CategoriesScreen(
						Modifier
							.fillMaxSize()
							.padding(top = 32.dp)
							.background(Color.White),
						navController,
						viewModel
					)
				}
				composable(Screen.Home.route) {
					MainScreen(
						Modifier
							.fillMaxSize()
							.padding(top = 32.dp)
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
					ProfileScreenContent(
						Modifier
							.fillMaxSize(),
						navController,
						viewModel
					)
				}
				composable(Screen.Language.route) {
					LanguageScreenContent(
						Modifier
							.fillMaxSize(),
						navController,
						viewModel
					)
				}
				composable(Screen.TermsConditions.route) {
					ScrollableText(
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
}
        
        


