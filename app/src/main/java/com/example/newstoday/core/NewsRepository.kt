package com.example.newstoday.core

import com.example.newstoday.core.network.NewsApiService
import com.example.newstoday.core.network.NewsResponse
import retrofit2.Call


class NewsRepository(private val apiService: NewsApiService) {

    fun getEverything(query: String): Call<NewsResponse> {
        return apiService.getEverything(query)
    }

    fun getTopHeadlines(country: String, apiKey: String): Call<NewsResponse> {
        return apiService.getTopHeadlines(country, apiKey)
    }
}