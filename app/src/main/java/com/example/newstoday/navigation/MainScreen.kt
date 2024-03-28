package com.example.newstoday.navigation


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newstoday.views.SampleView
import com.example.newstoday.views.SampleView2
import com.example.newstoday.views.SampleView3
import com.example.newstoday.views.SampleView4

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val items = listOf(
        Screen.Home,
        Screen.Categories,
        Screen.Bookmarks,
        Screen.Profile,
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(items, navController) }
    ) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                SampleView(modifier = Modifier.fillMaxSize())
            }
            composable(Screen.Categories.route) {
                SampleView2(modifier = Modifier.fillMaxSize())
            }
            composable(Screen.Bookmarks.route) {
                SampleView3(modifier = Modifier.fillMaxSize())
            }
            composable(Screen.Profile.route) {
                SampleView4(modifier = Modifier.fillMaxSize())
            }
        }
    }
}