package com.github.aoreshin.android.messenger.ui.data.remote.repository

import com.github.aoreshin.android.messenger.ui.data.vo.ConversationListVO
import com.github.aoreshin.android.messenger.ui.data.vo.ConversationVO
import io.reactivex.Observable

interface ConversationRepository {
    fun findConversationById(id: Long): Observable<ConversationVO>
    fun all(): Observable<ConversationListVO>
}