package ru.senin.kotlin.android.activityapplication.db

import androidx.lifecycle.LiveData
import ru.senin.kotlin.android.activityapplication.db.Message

class MessagesRepository(private val dao: MessagesDao) {

    val messages: LiveData<List<Message>> = dao.allMessages()

    suspend fun insert(message: Message) {
        dao.insertAllMessages(message)
    }

    suspend fun delete(message: Message) {
        dao.deleteMessage(message)
    }

    suspend fun deleteAll() {
        dao.deleteAllMessages()
    }
}