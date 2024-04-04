package com.example.newstoday.core.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_entity")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val author: String?,
    val title: String,
    val content: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val imageData: ByteArray?
)