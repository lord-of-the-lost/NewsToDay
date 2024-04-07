package com.example.newstoday.core.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_entity")
data class UserData(
    @PrimaryKey(true)
    val id: Int = 1,
    val email: String,
    val name: String,
    val password: String
)

