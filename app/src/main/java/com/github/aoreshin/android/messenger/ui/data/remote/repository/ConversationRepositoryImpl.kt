package com.github.aoreshin.android.messenger.ui.data.remote.repository

import android.content.Context
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.vo.ConversationListVO
import com.github.aoreshin.android.messenger.ui.data.vo.ConversationVO
import com.github.aoreshin.android.messenger.ui.service.MessengerApiService
import io.reactivex.Observable

class ConversationRepositoryImpl(ctx: Context) : ConversationRepository {
    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findConversationById(id: Long): Observable<ConversationVO> {
        return service.showConversation(id, preferences.accessToken as String)
    }

    override fun all(): Observable<ConversationListVO> {
        return service.listConversations(preferences.accessToken as String)
    }
}