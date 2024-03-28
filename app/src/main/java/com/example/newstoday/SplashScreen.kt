package com.example.newstoday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.ui.theme.NewsToDayTheme
import com.example.newstoday.ui.theme.adamina

@Composable
fun SplashScreen() {
	val paddingHoriz = 77.dp
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color(0xFF2536A7))
	) {
		Text(
			modifier = Modifier
				.padding(
					start = paddingHoriz,
					//top = 303.dp //при непрозрачном статус баре
					top = 303.dp  //при прозрачном статус баре
				
				),
			text = "News",
			fontFamily = adamina,
			fontWeight = FontWeight.Normal,
			fontSize = 64.sp,
			color = Color(0xFFEEF0FB),
			style = TextStyle(shadow = Shadow(Color(0x40000000), Offset(0f, 4f), 4.0f))
		)
		Text(
			modifier = Modifier
				.padding(
					end = paddingHoriz,
					//top = 370.dp //при непрозрачном статус баре
					top = 398.dp  //при прозрачном статус баре
				)
				.align(Alignment.TopEnd),
			text = "ToDay",
			fontFamily = adamina,
			fontWeight = FontWeight.Normal,
			fontSize = 64.sp,
			color = Color(0xFFEEF0FB),
			style = TextStyle(shadow = Shadow(Color(0x40000000), Offset(0f, 4f), 4.0f))
		)
	}
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
	NewsToDayTheme {
		SplashScreen()
	}
}