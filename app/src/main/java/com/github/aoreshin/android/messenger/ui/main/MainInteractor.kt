package com.github.aoreshin.android.messenger.ui.main

import com.github.aoreshin.android.messenger.ui.data.vo.ConversationListVO
import com.github.aoreshin.android.messenger.ui.data.vo.UserListVO

interface MainInteractor {
    interface OnConversationsLoadFinishedListener {
        fun onConversationsLoadSuccess(conversationsListVo: ConversationListVO)
        fun onConversationsLoadError()
    }

    interface OnContactsLoadFinishedListener {
        fun onContactsLoadSuccess(userListVO: UserListVO)
        fun onContactsLoadError()
    }

    interface OnLogoutFinishedListener {
        fun onLogoutSuccess()
    }

    fun loadContacts(listener: MainInteractor.OnContactsLoadFinishedListener)

    fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishedListener)

    fun logout(listener: MainInteractor.OnLogoutFinishedListener)
}