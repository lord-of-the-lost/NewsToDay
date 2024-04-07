package com.example.newstoday.core.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.Response

interface NewsApiService {
    @Headers("x-api-key: 9eb135314d134f44b60d88e096de26f6")

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String,
    ): Response<NewsResponse>
}

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)