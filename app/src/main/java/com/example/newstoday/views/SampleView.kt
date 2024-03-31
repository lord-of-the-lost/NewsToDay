package com.example.newstoday.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newstoday.core.NewsViewModel

@Composable
fun SampleView(modifier: Modifier, viewModel: NewsViewModel) {
    val newsResponse = viewModel.newsResponse.value
    val errorMessage = viewModel.errorMessage.value

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when {
            newsResponse != null -> {
                Text(text = newsResponse.articles.firstOrNull()?.title ?: "Заголовок не найден")
            }
            errorMessage != null -> {
                Text(text = "Ошибка: $errorMessage")
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
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