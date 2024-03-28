package com.example.newstoday.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun MainScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 200.dp)
    ) {
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
                        .padding(end = 16.dp),
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

        LazyRow(
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
//                    .align(Alignment.End)
//                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}