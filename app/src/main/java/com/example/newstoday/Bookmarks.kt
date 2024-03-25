package com.example.newstoday

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter


@Composable
fun Booksmarks() {
    var listNews by remember { mutableStateOf<List<NewsArticle>>(emptyList()) }
    listNews = createSampleNewsArticles()
    Box(contentAlignment = Alignment.Center) {
        if (listNews.isEmpty()) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray, RoundedCornerShape(100))
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "You haven't saved any articles yet. \n Start reading and bookmarking them now",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        } else {
            LazyColumn {
                items(listNews) { it ->
                    CardNews(newsArticle = it, onArticlePage = {})
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNews(newsArticle: NewsArticle, onArticlePage: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        onClick = { onArticlePage }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilImage(url = newsArticle.urlToImage.toString(), contentDescription = "")

            Box {


                Text(
                    text = newsArticle.author ?: "",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp,
                        color = Color.Black
                    ),
                    color = Color.Gray,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(bottom = 70.dp),

                    )
                Text(
                    text = newsArticle.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Left,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
//                        .padding(bottom = 30.dp)
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
            .size(90.dp)
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(15)),
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
                Log.e(
                    "CoilImage",
                    "Failed to load image: ${(painter.state as ImagePainter.State.Error).throwable}"
                )
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


fun createSampleNewsArticles(): List<NewsArticle> {
    val articles = mutableListOf<NewsArticle>()

    // News article 1
    val source1 = NewsSource(id = null, name = "Marketscreener.com")
    val article1 = NewsArticle(
        source = source1,
        author = null,
        title = "Solana Leads Way As Most Big Cryptocurrencies Post Gains",
        description = "(marketscreener.com) \n This article was automatically generated by MarketWatch using technology from Automated Insights. \n\n\n Most large cryptocurrencies were up during morning trading on Monday, with Solana seeing the biggest move, climbing 6.53% to $190.64. …",
        url = "https://www.marketscreener.com/quote/cryptocurrency/SOLANA-SOL-BTC-127916513/news/Solana-Leads-Way-As-Most-Big-Cryptocurrencies-Post-Gains-46277130/",
        urlToImage = "https://www.marketscreener.com/images/twitter_MS_fdblanc.png",
        publishedAt = "2024-03-25T14:16:54Z",
        content = "This article was automatically generated by MarketWatch using technology from Automated Insights. \r\nMost large cryptocurrencies were up during morning trading on Monday, with Solana seeing the bigges… [+1339 chars]"
    )
    articles.add(article1)

    // News article 2
    val source2 = NewsSource(id = null, name = "Biztoc.com")
    val article2 = NewsArticle(
        source = source2,
        author = "benzinga.com",
        title = "Tesla's Tactical Change May Deliver Fewer Vehicles But Could Raise Profitability: Analyst",
        description = "Tesla Inc TSLA appears to be changing its strategy after noting in its last quarterly earnings report that it was facing lower demand for electric vehicles and that delivery growth rates in 2024 would be “notably lower” than in 2023. The company’s fourth-quar…",
        url = "https://biztoc.com/x/575a8ed47d52d396",
        urlToImage = "https://c.biztoc.com/p/575a8ed47d52d396/s.webp",
        publishedAt = "2024-03-25T16:26:18Z",
        content = "Tesla Inc TSLA appears to be changing its strategy after noting in its last quarterly earnings report that it was facing lower demand for electric vehicles and that delivery growth rates in 2024 woul… [+301 chars]"
    )
    articles.add(article2)

    // News article 3
    val source3 = NewsSource(id = null, name = "Biztoc.com")
    val article3 = NewsArticle(
        source = source3,
        author = "fortune.com",
        title = "Tesla offers a CyberHammer to people who help sell its cars",
        description = "Tesla might make its numbers on the sale of its vehicles, but the automaker has a curious side gig: offering head-scratching products. Past offerings have included Tesla-branded bottle openers, tequila, and beer. Now you can add sledgehammers to the list. The…",
        url = "https://biztoc.com/x/145525bf59633574",
        urlToImage = "https://c.biztoc.com/p/145525bf59633574/s.webp",
        publishedAt = "2024-03-25T16:22:09Z",
        content = "Tesla might make its numbers on the sale of its vehicles, but the automaker has a curious side gig: offering head-scratching products. Past offerings have included Tesla-branded bottle openers, tequi… [+301 chars]"
    )
    articles.add(article3)

    // News article 4
    val source4 = NewsSource(id = null, name = "CleanTechnica")
    val article4 = NewsArticle(
        source = source4,
        author = "Jennifer Sensiba",
        title = "US DOJ Gives Us Another Reason Automakers Might Be Ditching Apple CarPlay",
        description = "A recent article I read at The Autopian sheds some important light on a controversial decision some automakers have been making: to ditch Android Auto and Apple CarPlay. A few months ago, word from GM was that flaky connections between cars and vehicles was t…",
        url = "https://cleantechnica.com/2024/03/25/us-doj-gives-us-another-reason-automakers-might-be-ditching-apple-carplay/",
        urlToImage = "https://cleantechnica.com/wp-content/uploads/2024/03/Apple-Vision-Pro-Press-Photo.png",
        publishedAt = "2024-03-25T16:15:32Z",
        content = "Sign up for daily news updates from CleanTechnica on email. Or follow us on Google News!\r\nA recent article I read at The Autopiansheds some important light on a controversial decision some automakers… [+6242 chars]"
    )
    articles.add(article4)

    return articles
}

@Preview(showBackground = true)
@Composable
fun BooksmarksPreview() {
    Booksmarks()
}