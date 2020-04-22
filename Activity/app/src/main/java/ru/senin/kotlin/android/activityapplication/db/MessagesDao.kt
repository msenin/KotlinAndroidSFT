package ru.senin.kotlin.android.activityapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MessagesDao {

    @Query("SELECT * FROM Messages ORDER BY timestamp DESC")
    fun allMessages(): LiveData<List<Message>>

    @Query("SELECT count(*) FROM Messages")
    fun countMessages(): Int

    @Insert
    suspend fun insertAllMessages(vararg messages: Message)

    @Delete
    suspend fun deleteMessage(message: Message)

    @Query("DELETE FROM Messages")
    suspend fun deleteAllMessages()

    @Update
    fun updateMessages(vararg messages: Message)

    @Query("SELECT * FROM Users")
    fun allUsers(): LiveData<List<User>>

    @Query("SELECT count(*) FROM Users")
    fun countUsers(): Int

    @Insert
    suspend fun insertAllUsers(vararg users: User)

    @Delete
    suspend fun deleteUser(users: User)

    @Query("DELETE FROM Users")
    suspend fun deleteAllUsers()

    @Update
    fun updateUser(vararg users: User)
}