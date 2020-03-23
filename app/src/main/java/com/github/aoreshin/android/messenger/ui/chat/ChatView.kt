package com.github.aoreshin.android.messenger.ui.chat

import com.github.aoreshin.android.messenger.ui.base.BaseView
import com.github.aoreshin.android.messenger.utils.message.Message
import com.stfalcon.chatkit.messages.MessagesListAdapter

interface ChatView : BaseView {
    interface ChatAdapter {
        fun navigateToChat(recipientName: String, recipientId: Long,
                           conversationId: Long? = null)
    }

    fun showConversationLoadError()
    fun showMessageSendError()
    fun getMessageListAdapter(): MessagesListAdapter<Message>
}