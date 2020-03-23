package com.github.aoreshin.android.messenger.ui.main

import android.content.Context
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.remote.repository.ConversationRepository
import com.github.aoreshin.android.messenger.ui.data.remote.repository.ConversationRepositoryImpl
import com.github.aoreshin.android.messenger.ui.data.remote.repository.UserRepository
import com.github.aoreshin.android.messenger.ui.data.remote.repository.UserRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractorImpl(val context: Context) : MainInteractor {
    private val userRepository: UserRepository = UserRepositoryImpl(context)
    private val conversationRepository: ConversationRepository = ConversationRepositoryImpl(context)

    override fun loadContacts(listener: MainInteractor.OnContactsLoadFinishedListener) {
        userRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                listener.onContactsLoadSuccess(res)
            }, { error ->
                listener.onContactsLoadError()
                error.printStackTrace()
            })
    }

    override fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishedListener) {
        conversationRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                listener.onConversationsLoadSuccess(res)
            }, { error ->
                listener.onConversationsLoadError()
                error.printStackTrace()
            })
    }

    override fun logout(listener: MainInteractor.OnLogoutFinishedListener) {
        val preferences: AppPreferences = AppPreferences.create(context)
        preferences.clear()
        listener.onLogoutSuccess()
    }
}