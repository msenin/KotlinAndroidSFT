package ru.senin.kotlin.android.activityapplication.client

/**
 * Участник чата
 */
class Member(val memberId: Int, val displayName: String, val memberUserId: String, chat: Chat) : ChatAware(chat)