package com.example.newstoday.views.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R
import com.example.newstoday.ui.theme.inter
import com.example.newstoday.views.mainScreen.recommended.CardNews
import com.example.newstoday.views.mainScreen.recommended.RecommendedHeader
import com.example.newstoday.views.mainScreen.recommended.RecommendedNewsArticle
import com.example.newstoday.views.mainScreen.recommended.createSampleNewsArticles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var recommendedNewsList by remember { mutableStateOf<List<RecommendedNewsArticle>>(emptyList()) }
    recommendedNewsList = createSampleNewsArticles()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 168.dp)
            .background(Color.White),
    ) {
        item {
            val searchText = remember {
                mutableStateOf("")
            }

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 24.dp),
                shape = RoundedCornerShape(12.dp),
                colors = SearchBarDefaults.colors(containerColor = Color(0xFFF3F4F6)),
                query = searchText.value,
                onQueryChange = { text ->
                    searchText.value = text
                },
                onSearch = {
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.search_magnifier),
                        contentDescription = "SearchIcon",
                        tint = Color(0xFF7C82A1)
                    )
                },
                placeholder = {
                    Row(
                    ) {
                        Text(
                            text = "Search",
                            color = Color(0xFF7C82A1),
                            fontFamily = inter,
                            lineHeight = 24.sp,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                        )
                    }
                },
                active = false,
                onActiveChange = {
                }
            ) {
            }

            val categoriesList =
                listOf("Random", "Sportscghfdg", "Li", "Gaming", "Politics", "Animals")
            val activeCategoryIndex = remember { mutableIntStateOf(0) }

            LazyRow(    //category buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, start = 20.dp),     /*TODO*/
                horizontalArrangement = Arrangement.Absolute.spacedBy(16.dp)
            ) {
                itemsIndexed(
                    categoriesList
                ) { index, item ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(32.dp)
                            .clickable { activeCategoryIndex.value = index }
                            .background(
                                Color(
                                    if (index == activeCategoryIndex.value)
                                        0xFF475AD7
                                    else
                                        0xFFF3F4F6
                                )
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 2.dp),
                            text = item,
                            fontFamily = inter,
                            fontWeight = FontWeight(600),
                            fontSize = 12.sp,
                            color = Color(
                                if (index == activeCategoryIndex.value)
                                    0xFFFFFFFF
                                else
                                    0xFF7C82A1
                            ),
                        )
                    }

                }
            }

            LazyRow(    //news cards
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),     /*TODO*/
                horizontalArrangement = Arrangement.Absolute.spacedBy(16.dp)
            ) {
                items(cardList) { card ->
                    CardItem(card)
                }
            }
            RecommendedHeader()
        }

//      Recommended items
        items(recommendedNewsList) { it ->
            CardNews(newsArticle = it, onArticlePage = {})
        }
    }

}


@Composable
fun CardItem(card: CardInfo) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0x0022242F), Color(0x7A22242F))
    )

    Card(
    ) {
        Box(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp)
                .fillMaxWidth()
                .clickable { }
        ) {
            Image(
                painter = painterResource(id = card.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
                    .clip(shape = RoundedCornerShape(12.dp)),
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
                    .padding(
                        24.dp
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(24.dp),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier
                                .size(14.dp, 20.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.bookmark),
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = card.category.uppercase(),
                        fontFamily = inter,
                        lineHeight = 16.sp,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF3F4F6),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )

                    Text(
                        text = card.title,
                        fontFamily = inter,
                        lineHeight = 24.sp,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}