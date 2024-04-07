package com.example.newstoday.views.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newstoday.R
import com.example.newstoday.ui.theme.inter

@Composable
fun TopAppBarCust(
    screen: String, navController: NavController, modifier: Modifier
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (screen.contains(stringResource(id = R.string.terms_conditions)) || screen.contains(
                stringResource(id = R.string.language_language)
            )
        ) {
            {
                IconButton(
                    onClick = { navController.navigateUp() },
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
        stringResource(id = R.string.select_favorite),
        stringResource(id = R.string.welcome_back),
        stringResource(id = R.string.welcome_to_news),
        stringResource(id = R.string.browse),
        stringResource(id = R.string.categories),
        stringResource(id = R.string.bookmarks) -> {
            {
                val categoryText = when (screen) {
                    stringResource(id = R.string.select_favorite) -> stringResource(id = R.string.select_favorite_description)
                    stringResource(id = R.string.welcome_back) -> stringResource(id = R.string.welcome_back_description)
                    stringResource(id = R.string.welcome_to_news) -> stringResource(id = R.string.welcome_to_news_description)
                    stringResource(id = R.string.browse) -> stringResource(id = R.string.browse_description)
                    stringResource(id = R.string.categories) -> stringResource(id = R.string.categories_description)
                    stringResource(id = R.string.bookmarks) -> stringResource(id = R.string.bookmarks_description)
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
			.background(Color.White)
			.padding(top = 24.dp, start = 20.dp, end = 20.dp)
			.statusBarsPadding()
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
                    textAlign = if (screen.contains(stringResource(id = R.string.terms_conditions)) || screen.contains(
                            stringResource(id = R.string.language_language)
                        )
                    ) TextAlign.Center else TextAlign.Start,
                    modifier = Modifier
						.fillMaxWidth()
						.offset(
							x = if (screen.contains(stringResource(id = R.string.terms_conditions)) || screen.contains(
									stringResource(id = R.string.language_language)
								)
							) (-10).dp else 0.dp
						)
                )
                if (titleAdd != null) {
                    titleAdd()
                }
            }
        }
    }
}


