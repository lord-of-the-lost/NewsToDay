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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.newstoday.R
import com.example.newstoday.core.ArticleModel
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.ui.theme.inter
import com.example.newstoday.views.mainScreen.recommended.CardNews
import com.example.newstoday.views.mainScreen.recommended.RecommendedHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {

    var recommendedNewsList by remember { mutableStateOf<List<ArticleModel>>(emptyList()) }
    recommendedNewsList = viewModel.recomendedNewsResponse.value ?: emptyList()

    LazyColumn(
        modifier = modifier
    ) {
        item {
            val searchText = remember {
                mutableStateOf("")
            }

            //region SearchBar
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
                    viewModel.loadEverything(it)
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
                            text = stringResource(id = androidx.appcompat.R.string.search_menu_title),
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
            //endregion

            val  categoriesList = listOf(
                stringResource(id = R.string.random_Main),
                stringResource(id = R.string.sports_Main),
                stringResource(id = R.string.politics_Main),
                stringResource(id = R.string.life_Main),
                stringResource(id = R.string.gaming_Main),
                stringResource(id = R.string.animals_Main),
                stringResource(id = R.string.nature_Main),
                stringResource(id = R.string.food_Main),
                stringResource(id = R.string.art_Main),
                stringResource(id = R.string.history_Main),
                stringResource(id = R.string.fashion_Main)
            )
            val activeCategoryIndex = remember { mutableIntStateOf(0) }

            LazyRow(    //category tags-buttons
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, start = 20.dp),
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
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.Absolute.spacedBy(16.dp)
            ) {
                items(viewModel.bigItemsResponse.value ?: emptyList()) { card ->
                    CardItem(card)
                }
            }
            RecommendedHeader()
        }

//      Recommended items
        if (recommendedNewsList.isEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    text = "You haven't selected any categories yet. Go to the categories tab to select them",
                    color = Color(0xFF333647),
                    fontFamily = inter,
                    lineHeight = 24.sp,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                )
            }
        } else {
            items(recommendedNewsList) { it ->
                CardNews(article = it, navController)
            }
        }
    }
}

@Composable
fun CardItem(article: ArticleModel) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0x0022242F), Color(0x7A22242F))
    )
    Card(
    ) {

        var isBookmarkedArticle by remember {
            mutableStateOf(article.isBookmarked)
        }

        Box(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp)
                .fillMaxWidth()
                .clickable { }
        ) {
            Image(
                painter = rememberImagePainter(article.urlToImage),
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
                        onClick = {
                            isBookmarkedArticle = !isBookmarkedArticle
                            article.isBookmarked = isBookmarkedArticle
                        }) {
                        Icon(
                            modifier = Modifier
                                .size(14.dp, 20.dp),
                            imageVector = ImageVector.vectorResource(
                                if (isBookmarkedArticle)
                                    R.drawable.selected_bookmark
                                else
                                    R.drawable.bookmark
                            ),
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
                        text = article.tag.uppercase(),
                        fontFamily = inter,
                        lineHeight = 16.sp,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF3F4F6),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )

                    Text(
                        text = article.title,
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

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}