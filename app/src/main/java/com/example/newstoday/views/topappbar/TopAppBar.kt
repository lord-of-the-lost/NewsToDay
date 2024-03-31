package com.example.newstoday.views.topappbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R
import com.example.newstoday.ui.theme.NewsToDayTheme
import com.example.newstoday.ui.theme.inter

@Composable
fun TopAppBar(
	screen: String, backToProfile: () -> Unit
) {
	val navigationIcon: (@Composable () -> Unit)? =
		if (screen.contains("Terms & Conditions") || screen.contains("Language")) {
			{
				IconButton(
					onClick = ({ /*onBackNavClicked()*/ }),
					modifier = Modifier
						.size(24.dp),
				) {
					Icon(
						modifier = Modifier
							.size(12.dp),
						imageVector = ImageVector.vectorResource(R.drawable.back),
						tint = Color(0xFF7C82A1),
						contentDescription = null
					)
				}
			}
		} else {
			null
		}
	val titleAdd: (@Composable () -> Unit)? = when (screen) {
		"Select your favorite topics",
		"Welcome Back \uD83D\uDC4B",
		"Welcome to NewsToDay",
		"Browse", "Categories",
		"Bookmarks" -> {
			{
				val categoryText = when (screen) {
					"Select your favorite topics" -> "Select some of your favorite topics to let us suggest better news for you."
					"Welcome Back \uD83D\uDC4B" -> "I am happy to see you again. You can continue where you left off by logging in"
					"Welcome to NewsToDay" -> "Hello, I guess you are new around here. You can start using the application after sign up."
					"Browse" -> "Discover things of this world"
					"Categories" -> "Thousands of articles in each category"
					"Bookmarks" -> "Saved articles to the library"
					else -> ""
				}
				Text(
					text = categoryText,
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight.W400,
						lineHeight = 24.sp,
						color = Color(0xFF7C82A1)
					),
					fontFamily = inter,
					modifier = Modifier.padding(top = 8.dp)
				)
			}
		}
		
		else -> null
	}
	
	Box(
		modifier = Modifier
			.padding(top = 28.dp, start = 20.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically
		) {
			if (navigationIcon != null) {
				navigationIcon()
			}
			Spacer(modifier = Modifier.width(8.dp))
			Column(
				modifier = Modifier
					.offset(x = (-8).dp)
			) {
				Text(
					text = screen,
					style = TextStyle(
						fontSize = 24.sp,
						fontWeight = FontWeight.W600,
						lineHeight = 32.sp,
						color = Color(0xFF333647)
					),
					textAlign = if (screen.contains("Terms & Conditions") || screen.contains("Language")) TextAlign.Center else TextAlign.Start,
					modifier = Modifier
						.fillMaxWidth()
						.offset(x = if (screen.contains("Terms & Conditions") || screen.contains("Language")) (-20).dp else 0.dp)
				)
				if (titleAdd != null) {
					titleAdd()
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
	NewsToDayTheme {
		TopAppBar(screen = "Language") {}
	}
}