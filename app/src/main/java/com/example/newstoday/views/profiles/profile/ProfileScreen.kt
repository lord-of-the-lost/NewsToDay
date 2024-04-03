package com.example.newstoday.views.profiles.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R
import com.example.newstoday.ui.theme.NewsToDayTheme
import com.example.newstoday.ui.theme.inter
import com.example.newstoday.views.profiles.UserData
import com.example.newstoday.views.topappbar.TopAppBarCust

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
	toLanguageScreen: () -> Unit,
	toTermsConditionsScreen: () -> Unit,
	signOut: () -> Unit
) {
	Scaffold(
		topBar = {
			TopAppBarCust(
				screen = stringResource(id = R.string.profile),
				backToProfile = { })
		}
	) {
		ProfileScreenContent(
			{ toLanguageScreen() },
			{ toTermsConditionsScreen() },
			{ signOut() }
		)
	}
}

@Composable
fun ProfileScreenContent(
	toLanguageScreen: () -> Unit,
	toTermsConditionsScreen: () -> Unit,
	signOut: () -> Unit
) {
	
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		Row(
			modifier = Modifier
				.padding(top = 28.dp, start = 20.dp)
				.fillMaxWidth()
		) {
			ProfileImageTest()
			val s = UserData("Test", "Test@email.ru")
			ProfileInfoTest(userData = s)
		}
		
		Spacer(
			modifier = Modifier
				.height(44.dp)
		)
		
		Button(
			onClick = { toLanguageScreen() },
			modifier = Modifier
				.height(56.dp)
				.padding(start = 20.dp, end = 20.dp)
				.fillMaxWidth(),
			shape = RoundedCornerShape(12.dp),
			colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier
					.fillMaxWidth()
			) {
				Text(
					text = stringResource(id = R.string.language_language),
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.W600,
						lineHeight = 24.sp,
						color = Color(0xFF666C8E),
						fontFamily = inter
					),
					textAlign = TextAlign.Start,
				)
				Image(
					painter = painterResource(id = R.drawable.vector_button),
					contentDescription = null,
					modifier = Modifier
						.width(6.25.dp)
						.height(10.49.dp),
					alignment = Alignment.Center
				)
			}
		}
		
		Spacer(
			modifier = Modifier
				.height(240.dp)
		)
		
		Button(
			onClick = { toTermsConditionsScreen() },
			modifier = Modifier
				.height(56.dp)
				.padding(start = 20.dp, end = 20.dp)
				.fillMaxWidth(),
			shape = RoundedCornerShape(12.dp),
			colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier
					.fillMaxWidth()
			) {
				Text(
					text = stringResource(id = R.string.terms_conditions),
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.W600,
						lineHeight = 24.sp,
						color = Color(0xFF666C8E),
						fontFamily = inter
					),
					textAlign = TextAlign.Start,
				)
				Image(
					painter = painterResource(id = R.drawable.vector_button),
					contentDescription = null,
					modifier = Modifier
						.width(6.25.dp)
						.height(10.49.dp),
					alignment = Alignment.Center
				)
			}
		}
		Spacer(
			modifier = Modifier
				.height(28.dp)
		)
		
		Button(
			onClick = { signOut() },
			modifier = Modifier
				.height(56.dp)
				.padding(start = 20.dp, end = 20.dp)
				.fillMaxWidth(),
			shape = RoundedCornerShape(12.dp),
			colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier
					.fillMaxWidth()
			) {
				Text(
					text = stringResource(id = R.string.sign_out),
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.W600,
						lineHeight = 24.sp,
						color = Color(0xFF666C8E),
						fontFamily = inter
					),
					textAlign = TextAlign.Start,
				)
				Image(
					painter = painterResource(id = R.drawable.vector_button),
					contentDescription = null,
					modifier = Modifier
						.width(6.25.dp)
						.height(10.49.dp),
					alignment = Alignment.Center
				)
			}
		}
	}
}

@Composable
fun ProfileImageTest() {
	Box(
		modifier = Modifier
			.size(72.dp)
			.clip(RoundedCornerShape(100)),
		contentAlignment = Alignment.BottomStart
	) {
		Image(
			painter = painterResource(id = R.drawable._01),
			contentDescription = "profile picture",
			Modifier
				.width(72.dp)
				.height(72.dp)
		)
	}
}

@Composable
fun ProfileInfoTest(userData: UserData) {
	Column(
		modifier = Modifier
			.padding(horizontal = 16.dp, vertical = 16.dp)
	) {
		
		Text(
			text = userData.name,
			style = TextStyle(
				fontSize = 16.sp,
				fontWeight = FontWeight.W600,
				lineHeight = 24.sp,
				color = Color(0xFF333647),
				fontFamily = inter
			),
			textAlign = TextAlign.Center
		)
		Text(
			text = userData.email,
			style = TextStyle(
				fontSize = 14.sp,
				fontWeight = FontWeight.W400,
				lineHeight = 24.sp,
				color = Color(0xFF7C82A1),
				fontFamily = inter
			),
			textAlign = TextAlign.Center
		)
	}
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenTestPreview() {
	NewsToDayTheme {
		ProfileScreen({}, {}, {})
	}
}