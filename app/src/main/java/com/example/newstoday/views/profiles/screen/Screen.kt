package com.example.newstoday.views.profiles.screen

sealed class Screen(val route: String) {
    object ProfileScreen : Screen("profile_screen")
    object TermsConditions : Screen("terms_conditions")
    object Language : Screen("language")
}