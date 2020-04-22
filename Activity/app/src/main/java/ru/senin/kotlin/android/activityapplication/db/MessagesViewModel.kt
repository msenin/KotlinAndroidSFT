package ru.senin.kotlin.android.activityapplication.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.senin.kotlin.android.activityapplication.db.Message

class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MessagesRepository( MessagesDatabase(application).dao())
    val messages = repository.messages

    fun insertMessage(message: Message) = viewModelScope.launch {
        repository.insert(message)
    }

    fun deleteMessage(message: Message) = viewModelScope.launch {
        repository.delete(message)
    }

    fun deleteAllMessages() = viewModelScope.launch {
        repository.deleteAll()
    }
}