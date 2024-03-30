package com.example.newstoday.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R

data class CardInfo(val title: String, val imageId: Int)
val cardList = listOf(
    CardInfo("News #1", R.drawable.test_news_img1),
    CardInfo("News #2", R.drawable.test_news_img2),
    CardInfo("News #3", R.drawable.test_news_img3),
    CardInfo("News #4", R.drawable.test_news_img4),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 168.dp)
    ) {

        val searchText = remember {
            mutableStateOf("")
        }

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
//            shape = SearchBarDefaults.,
//            colors = SearchBarColors.,
            query = searchText.value,
            onQueryChange = {text ->
                searchText.value = text
            },
            onSearch = {
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(end = 20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "SearchIcon",
                    tint = Color.Gray
                )
            },
            placeholder = {
                Row(
                ) {

                    Text(
                        text = "Search",
                        color = Color.Gray
                    )
                }
            },
            active = false,
            onActiveChange ={
            }
        ) {
        }



        LazyRow(    //categories
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            itemsIndexed(
                listOf("Random", "Sports", "Gaming", "Politics", "Life", "Animals")
            ){_, item ->
                Button(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .background(Color.Red),
//                    colors = ButtonDefaults.buttonColors(color = Color.Red),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = item,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600)
                    )
                }

            }
        }

        LazyRow(    //news cards
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(cardList) { card ->
                CardItem(card)
            }
        }
    }


}



@Composable
fun CardItem(card: CardInfo) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(250.dp)
        ) {
            Image(
                painter = painterResource(id = card.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = card.title,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight(700)

            )

            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
//                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}