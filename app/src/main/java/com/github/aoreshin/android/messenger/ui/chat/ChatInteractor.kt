package com.github.aoreshin.android.messenger.ui.chat

import com.github.aoreshin.android.messenger.ui.data.vo.ConversationVO

interface ChatInteractor {
    interface OnMessageSendFinishedListener {
        fun onSendSuccess()
        fun onSendError()
    }

    interface onMessageLoadFinishedListener {
        fun onLoadSuccess(conversationVO: ConversationVO)
        fun onLoadError()
    }

    fun sendMessage(recipientId: Long, message: String, listener: OnMessageSendFinishedListener)

    fun loadMessages(conversationId: Long, listener: onMessageLoadFinishedListener)
}