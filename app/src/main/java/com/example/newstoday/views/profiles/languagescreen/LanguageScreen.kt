package com.example.newstoday.views.profiles.languagescreen

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.example.newstoday.R
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.ui.theme.inter

enum class Language {
    ENGLISH, RUSSIAN
}

@Composable
fun LanguageScreenContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {
    val context = LocalContext.current
    var selectedLanguage by remember { mutableStateOf(loadSelectedLanguage(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        LanguageButton(
            text = "English",
            selected = selectedLanguage == Language.ENGLISH
        ) {
            selectedLanguage = Language.ENGLISH
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        LanguageButton(
            text = "Russian",
            selected = selectedLanguage == Language.RUSSIAN
        ) {
            selectedLanguage = Language.RUSSIAN
        }
    }

    LaunchedEffect(selectedLanguage) {
        val newLang = if (selectedLanguage == Language.ENGLISH) "en" else "ru"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(newLang)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLang))
        }
        saveSelectedLanguage(context, selectedLanguage)
    }
}

@Composable
fun LanguageButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
			.height(56.dp)
			.padding(start = 20.dp, end = 20.dp)
			.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = if (selected) ButtonDefaults.buttonColors(containerColor = Color(0xFF475AD7)) else ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF3F4F6)
        ),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 24.sp,
                    color = if (selected) Color.White else Color(0xFF666C8E),
                    fontFamily = inter
                )
            )
            if (selected) {
                Icon(
                    modifier = Modifier
                        .size(14.05.dp, 10.22.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.check),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    }
}

const val LANG_PREF_KEY = "selected_language"

fun saveSelectedLanguage(context: Context, language: Language) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(LANG_PREF_KEY, language.name).apply()
}

fun loadSelectedLanguage(context: Context): Language {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val languageName = sharedPreferences.getString(LANG_PREF_KEY, Language.ENGLISH.name)
    return Language.valueOf(languageName ?: Language.ENGLISH.name)
}
