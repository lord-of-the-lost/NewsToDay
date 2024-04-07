package com.example.newstoday.core.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_entity u ")
    suspend fun getAllUsers(): List<UserData>
    @Query("SELECT * FROM users_entity u WHERE u.email = :email")
    suspend fun getUserByEmail(email: String): UserData?
    @Query("SELECT * FROM users_entity WHERE id = :userId")
    suspend fun getEmail(userId: Int): UserData?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: UserData)

    @Delete
    suspend fun deleteUser(users: UserData)
    @Query("SELECT * FROM users_entity WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserData?
}