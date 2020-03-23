package com.github.aoreshin.android.messenger.ui.chat

import android.widget.Toast
import com.github.aoreshin.android.messenger.ui.data.vo.ConversationVO
import com.github.aoreshin.android.messenger.utils.message.Message
import java.text.SimpleDateFormat

class ChatPresenterImpl(val view: ChatView) : ChatPresenter,
    ChatInteractor.OnMessageSendFinishedListener,
    ChatInteractor.onMessageLoadFinishedListener {
    private val interactor: ChatInteractor = ChatInteractorImpl(view.getContext())

    override fun onLoadSuccess(conversationVO: ConversationVO) {
        val adapter = view.getMessageListAdapter()

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        conversationVO.messages.forEach { message ->
            adapter.addToStart(Message(message.senderId, message.body, dateFormatter.parse(message.createdAt)), true)
        }
    }

    override fun onLoadError() {
        view.showConversationLoadError()
    }

    override fun onSendSuccess() {
        Toast.makeText(view.getContext(), "Message sent", Toast.LENGTH_LONG).show()
    }

    override fun onSendError() {
        view.showMessageSendError()
    }

    override fun sendMessage(recipientId: Long, message: String) {
        interactor.sendMessage(recipientId, message,this)
    }

    override fun loadMessages(conversationId: Long) {
        interactor.loadMessages(conversationId, this)
    }
}