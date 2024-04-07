package com.example.newstoday.core


import com.example.newstoday.core.storage.UserData
import com.example.newstoday.core.storage.UsersDao

class UsersRepository(private val usersDao: UsersDao) {
    suspend fun saveUsers(user: UserData) {
        usersDao.insertUser(user)
    }
    suspend fun getUserByEmail(email: String): UserData? {
        return usersDao.getUserByEmail(email)
    }
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserData? {
        return usersDao.getUserByEmailAndPassword(email, password)
    }
}