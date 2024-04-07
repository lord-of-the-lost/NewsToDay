package com.example.newstoday.views.booksmark

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.newstoday.R
import com.example.newstoday.core.ArticleModel
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.navigation.Screen
import com.example.newstoday.ui.theme.inter


@SuppressLint("SuspiciousIndentation")
@Composable
fun Bookmarks(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {
    var listNews by remember { mutableStateOf<List<ArticleModel>>(emptyList()) }
    listNews = viewModel.savedArticles.value ?: emptyList()

    LaunchedEffect(key1 = true) {
        viewModel.getSavedArticles()
    }

    if (listNews.isEmpty()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .background(color = Color(0xFFEEF0FB), shape = RoundedCornerShape(100))
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(16.dp)
                        .height(20.dp)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.have_not_bookmarks),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
            )

        }

    } else {
        LazyColumn(
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            items(listNews) { it ->
                CardNews(newsArticle = it, onArticlePage = {
                    viewModel.selectedArticle.value = it
                    navController.navigate(Screen.NewsScreen.route)
                })
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(newsArticle: ArticleModel, onArticlePage: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        onClick = { onArticlePage() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .height(96.dp)
        ) {

            Image(
                painter = rememberImagePainter(newsArticle.urlToImage),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.White)
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .height(96.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = newsArticle.author,
                    style = TextStyle(
                        fontFamily = inter,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp,
                        color = Color(0xFF7C82A1)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = newsArticle.title,
                    style = TextStyle(
                        fontFamily = inter,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        color = Color(0xFF333647),
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}