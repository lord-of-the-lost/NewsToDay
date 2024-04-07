package com.example.newstoday.core.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}