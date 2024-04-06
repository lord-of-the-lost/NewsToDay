package com.example.newstoday

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.navigation.MainNavigationScreen
import com.example.newstoday.ui.theme.NewsToDayTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            false
        }
        //enableEdgeToEdge()
        val viewModel: NewsViewModel by viewModels()
        setContent {
            NewsToDayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MainNavigationScreen(viewModel)
                }
            }
        }
    }
}