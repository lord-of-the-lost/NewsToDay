package com.example.newstoday.views.profiles.termsconditions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newstoday.R
import com.example.newstoday.ui.theme.NewsToDayTheme
import com.example.newstoday.ui.theme.inter
import com.example.newstoday.views.profiles.screen.Screen
import com.example.newstoday.views.topappbar.TopAppBarCust


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TermsConditionsScreen(navController: NavHostController) {
	Scaffold(topBar = {
		TopAppBarCust(
			screen = stringResource(id = R.string.terms_conditions),
			backToProfile = { navController.navigate(Screen.ProfileScreen.route) })
	})
	
	{ innerPadding ->
		Column(
			modifier = Modifier
				.padding(innerPadding)
				.fillMaxSize()
		) {
			ScrollableText()
		}
	}
}

@Composable
fun ScrollableText() {
	
	Text(
		modifier = Modifier
			.padding(20.dp)
			.verticalScroll(rememberScrollState()),
		text = stringResource(id = R.string.terms_conditions_text),
		style = TextStyle(
			fontFamily = inter,
			fontSize = 16.sp,
			fontWeight = FontWeight.Normal,
			lineHeight = 24.sp,
			color = Color(0xFF7C82A1)
		),
	)
}


@Preview(showBackground = true)
@Composable
fun TermsConditionsScreenPreview() {
	NewsToDayTheme {
		ScrollableText()
	}
}