package com.example.newstoday.core

import com.example.newstoday.core.network.Article
import com.example.newstoday.core.network.NewsApiService
import com.example.newstoday.core.storage.ArticleDao
import com.example.newstoday.core.storage.ArticleEntity


class NewsRepository(private val apiService: NewsApiService, private val articleDao: ArticleDao) {
    suspend fun getEverything(query: String, apiKey: String): List<Article>? {
        val response = apiService.getEverything(query = query, apiKey = apiKey)
        return if (response.isSuccessful) response.body()?.articles else null
    }

    suspend fun getTopHeadlines(country: String, apiKey: String): List<Article>? {
        val response = apiService.getTopHeadlines(country = country, apiKey = apiKey)
        return if (response.isSuccessful) response.body()?.articles else null
    }

    suspend fun saveArticle(article: ArticleEntity) {
        articleDao.insertArticle(article)
    }

    suspend fun deleteArticle(article: ArticleEntity) {
        articleDao.deleteArticle(article)
    }

    suspend fun getSavedArticles(): List<ArticleEntity> {
        return articleDao.getAllArticles()
    }
}
