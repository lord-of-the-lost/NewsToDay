package com.example.newstoday.navigation


import androidx.compose.ui.graphics.Color
import com.example.newstoday.R

sealed class Screen(val route: String, val iconResourceId: Int, val title: String, val activeColor: Color) {
    object Home : Screen("home", R.drawable.home_botton_navigation, "Home", Color(0xFF475AD7))
    object Categories : Screen("categories", R.drawable.categories_botton_navigation, "Categories", Color(0xFF475AD7))
    object Bookmarks : Screen("bookmarks", R.drawable.bookmarks_botton_navigation, "Bookmarks", Color(0xFF475AD7))
    object Profile : Screen("profile", R.drawable.progile_botton_navigation, "Profile", Color(0xFF475AD7))
}