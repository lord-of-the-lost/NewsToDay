package com.example.newstoday.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstoday.core.network.Article
import com.example.newstoday.core.network.RetrofitClient
import kotlinx.coroutines.launch


class NewsViewModel : ViewModel() {
    private val apiService = RetrofitClient.create()
    private val repository = NewsRepository(apiService)
    private val apiKey = "9eb135314d134f44b60d88e096de26f6"
    var newsResponse = mutableStateOf<List<Article>?>(null)
    var errorMessage = mutableStateOf<String?>(null)

    init {
        loadTopHeadlines()
    }

    private fun loadTopHeadlines(country: String = "us") {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country, apiKey)
                if (response != null) {
                    newsResponse.value = response
                } else {
                    errorMessage.value = "Не удалось загрузить заголовки новостей."
                }
            } catch (e: Exception) {
                errorMessage.value = "Ошибка сети: ${e.message}"
            }
        }
    }

    fun loadEverything(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getEverything(query, apiKey)
                if (response != null) {
                    newsResponse.value = response
                } else {
                    errorMessage.value = "Не удалось загрузить все новости."
                }
            } catch (e: Exception) {
                errorMessage.value = "Ошибка сети: ${e.message}"
            }
        }
    }

    fun loadArticlesByCategories(categories: List<String>) {
        viewModelScope.launch {
            val results = mutableListOf<Article>()
            categories.forEach { category ->
                repository.getEverything(category, apiKey)?.let {
                    results.addAll(it)
                }
            }
            newsResponse.value = results.shuffled()
        }
    }
}
