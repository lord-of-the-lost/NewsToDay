package com.example.newstoday.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier
            .bottomBorder(BorderStroke(
                width = 1.dp,
                color = Color(0xFFACAFC3)
            ))
    ) {
        val currentRoute = currentRoute(navController)
        listOf(
            Screen.Home,
            Screen.CategoriesScreen,
            Screen.Bookmarks,
            Screen.Profile
        ).forEach { screen ->
            val isSelected = currentRoute == screen.route
            BottomNavigationItem(
                icon = {
                    screen.iconResourceId?.let { painterResource(id = it) }?.let {
                        (if (isSelected) screen.activeColor else Color(0xFFACAFC3))?.let { it1 ->
                            Icon(
                                painter = it,
                                contentDescription = null,
                                tint = it1,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

fun Modifier.bottomBorder(border: BorderStroke): Modifier = composed {
    this.then(
        Modifier.border(
            border = border,
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
        )
    )
}