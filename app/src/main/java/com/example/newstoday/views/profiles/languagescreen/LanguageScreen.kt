package com.example.newstoday.views.profiles.languagescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newstoday.R
import com.example.newstoday.views.profiles.profile.TopBar
import com.example.newstoday.views.profiles.screen.Screen

enum class Language {
    ENGLISH, RUSSIAN
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LanguageScreen(navController: NavHostController) {
    var selectedLanguage by remember { mutableStateOf(Language.ENGLISH) }
    Scaffold(topBar = {
        TopBar(
            screen = "Language",
            backToProfile = { navController.navigate(Screen.ProfileScreen.route) })
    }) {
        Box(
            modifier = Modifier
                .padding(top = 124.dp, start = 20.dp)
                .width(336.dp)
                .height(56.dp)
        ) {
            LanguageButton(
                text = "English",
                selected = selectedLanguage == Language.ENGLISH,
                onClick = { selectedLanguage = Language.ENGLISH }
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 196.dp, start = 20.dp)
                .width(336.dp)
                .height(56.dp)
        ) {
            LanguageButton(
                text = "Russian",
                selected = selectedLanguage == Language.RUSSIAN,
                onClick = { selectedLanguage = Language.RUSSIAN }
            )
        }
    }
}

@Composable
fun LanguageButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .width(336.dp)
            .height(56.dp)
            .clip(CutCornerShape(12)),
        colors = if (selected) ButtonDefaults.buttonColors(containerColor = Color(0xFF475AD7)) else ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF3F4F6)
        ),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = text, color = if (selected) Color.White else Color(0xFF666C8E))
            if (selected) {
                Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.check_4),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CutCornerShape(12))
                    )
            }
        }
    }
}