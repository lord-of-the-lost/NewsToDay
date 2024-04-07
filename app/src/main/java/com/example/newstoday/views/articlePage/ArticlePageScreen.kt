package com.example.newstoday.views.articlePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.newstoday.R
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.ui.theme.inter

@Composable
fun ArticlePageScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {
    val article = viewModel.selectedArticle.value
    var isBookmarked by remember { mutableStateOf(false) }
    isBookmarked = article?.isBookmarked == true

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0x0022242F), Color(0x7A22242F))
    )

    val uriHandler = LocalUriHandler.current

    if (article != null) {
        Column(
            modifier = Modifier
				.fillMaxSize()
				.offset(y = (-38).dp)
        ) {
            Box(
                modifier = Modifier
					.fillMaxWidth()
					.size(0.dp, 368.dp)
            ) {
                Image(
                    modifier = Modifier
						.fillMaxSize()
						.background(gradient),
                    painter = rememberImagePainter(article.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        Color(0x2922242F),
                        blendMode = BlendMode.Darken
                    )
                )
                Box(
                    modifier = Modifier
						.matchParentSize()
						.background(gradient)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
							.fillMaxWidth()
							.padding(
								start = 20.dp,
								top = 72.dp,
								end = 20.dp
							),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(24.dp),
                            onClick = { navController.popBackStack() }) {
                            Icon(
                                modifier = Modifier
                                    .size(12.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.back),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                        IconButton(
                            modifier = Modifier
                                .size(24.dp),
                            onClick = {
                                if (isBookmarked != true) {
                                    isBookmarked = true
                                    article.isBookmarked = isBookmarked
                                    viewModel.saveArticle(article)
                                } else {
                                    isBookmarked = false
                                    article.isBookmarked = isBookmarked
                                    viewModel.deleteArticle(article)
                                }
                            }) {
                            Icon(
                                modifier = Modifier
                                    .size(14.dp, 20.dp),
                                imageVector = ImageVector.vectorResource(
                                    if (isBookmarked)
                                        R.drawable.selected_bookmark
                                    else
                                        R.drawable.bookmark
                                ),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
							.fillMaxWidth()
							.padding(
								start = 20.dp,
								top = 24.dp,
								end = 20.dp
							),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(24.dp),
                            onClick = {
                                article.id.let { uriHandler.openUri(it) }
                            }) {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp, 18.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.share),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
						.offset(20.dp, 120.dp)
						.clip(CircleShape)
						.background(Color(0xFF475AD7))
						.padding(16.dp, 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = article.tag,
                        fontFamily = inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                }
                Text(
                    text = article.title,
                    lineHeight = 28.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 168.dp, end = 20.dp)
                )
                Text(
                    text = article.author,
                    fontFamily = inter,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 26.dp, top = 296.dp)
                )
                Text(
                    text = stringResource(id = R.string.autor),
                    fontFamily = inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color(0xFFACAFC3),
                    modifier = Modifier
                        .padding(start = 26.dp, top = 320.dp)
                )
            }
            Text(
                text = article.content,
                lineHeight = 24.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color(0xFF666C8E),
                modifier = Modifier
					.padding(19.5.dp)
					.verticalScroll(rememberScrollState())
            )
        }
    }
}

