package ru.senin.kotlin.android.activityapplication.client

import java.text.SimpleDateFormat

/**
 * Сообщение
 */
class Message (val messageId: Int, val author: Member, val text: String, val createdOn: Long, chat: Chat) : ChatAware(chat) {
    companion object {
        val formatter: SimpleDateFormat = SimpleDateFormat("dd-MMM-yy HH:mm:ss")
    }
    override fun toString(): String {
        return "${author.displayName} (${author.memberUserId}) [${formatter.format(createdOn)}]: $text"
    }
}
