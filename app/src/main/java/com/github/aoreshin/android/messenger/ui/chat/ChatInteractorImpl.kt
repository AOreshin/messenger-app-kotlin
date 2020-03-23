package com.github.aoreshin.android.messenger.ui.chat

import android.content.Context
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.remote.repository.ConversationRepository
import com.github.aoreshin.android.messenger.ui.data.remote.repository.ConversationRepositoryImpl
import com.github.aoreshin.android.messenger.ui.data.remote.request.MessageRequestObject
import com.github.aoreshin.android.messenger.ui.service.MessengerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatInteractorImpl(context: Context) : ChatInteractor {
    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val conversationsRepository: ConversationRepository = ConversationRepositoryImpl(context)

    override fun loadMessages(conversationId: Long, listener: ChatInteractor.onMessageLoadFinishedListener) {
        conversationsRepository.findConversationById(conversationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res -> listener.onLoadSuccess(res)},
                { error ->
                    listener.onLoadError()
                    error.printStackTrace()})
    }

    override fun sendMessage(recipientId: Long, message: String,
                             listener: ChatInteractor.OnMessageSendFinishedListener) {
        service.createMessage(MessageRequestObject(recipientId, message), preferences.accessToken as String)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({listener.onSendSuccess()},
                { error ->
                    listener.onSendError()
                    error.printStackTrace()})
    }
}