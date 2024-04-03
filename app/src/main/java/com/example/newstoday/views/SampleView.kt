package com.example.newstoday.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.core.network.Article

@Composable
fun SampleView(modifier: Modifier = Modifier, viewModel: NewsViewModel) {
    val newsArticles = viewModel.newsResponse.value
    val errorMessage = viewModel.errorMessage.value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            newsArticles?.isNotEmpty() == true -> {
                LazyColumn {
                    items(newsArticles!!) { article ->
                        ArticleItem(article)
                    }
                }
            }
            !errorMessage.isNullOrEmpty() -> {
                Text(text = "Ошибка: $errorMessage")
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun ArticleItem(article: Article) {
    Text(text = article.title, modifier = Modifier.padding(8.dp))
}


@Composable
fun SampleView2(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(text = "SampleView2")
    }
}
@Composable
fun SampleView3(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(text = "SampleView3")
    }
}

@Composable
fun SampleView4(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(text = "SampleView4")
    }
}