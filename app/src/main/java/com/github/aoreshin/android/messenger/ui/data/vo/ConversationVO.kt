package com.github.aoreshin.android.messenger.ui.data.vo

data class ConversationVO (
    val conversationId: Long,
    val secondPartyUsername: String,
    val messages: ArrayList<MessageVO>
)