package com.example.newstoday.core.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_entity")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val author: String,
    val title: String,
    val content: String,
    val tag: String,
    val urlToImage: String,
)