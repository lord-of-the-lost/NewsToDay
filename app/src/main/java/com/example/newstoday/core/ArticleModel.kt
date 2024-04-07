package com.example.newstoday.core

data class ArticleModel(
    val id: String,
    val title: String,
    val content: String,
    val urlToImage: String,
    val author: String,
    val tag: String,
    var isBookmarked: Boolean
)
