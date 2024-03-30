package com.example.newstoday.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Home : Screen("home", Icons.Filled.Home, "Home")
    object Categories : Screen("categories", Icons.Filled.Menu, "Categories")
    object Bookmarks : Screen("bookmarks", Icons.Filled.Favorite, "Bookmarks")
    object Profile : Screen("profile", Icons.Filled.AccountCircle, "Profile")

}