package com.example.newstoday.core


import com.example.newstoday.core.storage.UserData
import com.example.newstoday.core.storage.UsersDao

class UsersRepository(private val usersDao: UsersDao) {
    suspend fun saveUsers(user: UserData) {
        usersDao.insertUser(user)
    }

    suspend fun deleteUser(user: UserData) {
        usersDao.deleteUser(user)
    }

    suspend fun getEmail(user: UserData): UserData? {
        return usersDao.getEmail(user.id)
    }
    suspend fun getAllUsers(): List<UserData> {
        return usersDao.getAllUsers()
    }
}