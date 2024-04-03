package com.example.newstoday.core

import com.example.newstoday.core.network.Article
import com.example.newstoday.core.network.NewsApiService


class NewsRepository(private val apiService: NewsApiService) {
    suspend fun getEverything(query: String, apiKey: String): List<Article>? {
        val response = apiService.getEverything(query = query, apiKey = apiKey)
        return if (response.isSuccessful) response.body()?.articles else null
    }

    suspend fun getArticlesForCategory(category: String, apiKey: String): List<Article>? {
        return getEverything(category, apiKey)
    }

    suspend fun getTopHeadlines(country: String, apiKey: String): List<Article>? {
        val response = apiService.getTopHeadlines(country = country, apiKey = apiKey)
        return if (response.isSuccessful) response.body()?.articles else null
    }
}
