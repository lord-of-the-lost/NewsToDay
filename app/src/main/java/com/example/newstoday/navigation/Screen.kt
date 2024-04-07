package com.example.newstoday.navigation


import androidx.compose.ui.graphics.Color
import com.example.newstoday.R

sealed class Screen(
    val route: String,
    val iconResourceId: Int? = null,
    val activeColor: Color? = null
) {
    object Onboarding : Screen("onboarding")
    object Registration : Screen("registration")
    object Authorization : Screen("authorization")
    object NewsScreen : Screen("news_screen")
    object TermsConditions : Screen("terms_conditions")
    object Language : Screen("language")
    object Home : Screen("home", R.drawable.home_botton_navigation, Color(0xFF475AD7))
    object CategoriesScreen :
        Screen("categories", R.drawable.categories_botton_navigation, Color(0xFF475AD7))
    object Bookmarks :
        Screen("bookmarks", R.drawable.bookmarks_botton_navigation, Color(0xFF475AD7))
    object Profile : Screen("profile", R.drawable.progile_botton_navigation, Color(0xFF475AD7))
}