package com.example.newstoday.views.mainScreen.recommended

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.newstoday.R
import com.example.newstoday.navigation.Screen
import com.example.newstoday.ui.theme.inter


@SuppressLint("SuspiciousIndentation")
@Composable
fun RecommendedHeader() {

    Spacer(modifier = Modifier.height(48.dp))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recommended for you",
                fontFamily = inter,
                fontWeight = FontWeight(600),
                fontSize = 20.sp,
                color = Color(0xFF333647),
                lineHeight = 24.sp,
            )
            Text(
                modifier = Modifier
                    .clickable { },
                text = "See All",
                fontFamily = inter,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                color = Color(0xFF7C82A1),
                lineHeight = 24.sp,
            )
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(newsArticle: RecommendedNewsArticle, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        onClick = { navController.navigate(Screen.NewsScreen.route) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .height(96.dp)

        ) {

            CoilImage(
                url = newsArticle.urlToImage ?: "",
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .height(96.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = newsArticle.category,
                    style = TextStyle(
                        fontFamily = inter,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp,
                        color = Color(0xFF7C82A1)
                    ),
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(
    url: String,
    contentDescription: String?,
) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
        }
    )

    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            is ImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.None,

                    )
            }

            is ImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.not_loaded),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.None,
                )
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(96.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendedPreview() {
    RecommendedHeader()

}