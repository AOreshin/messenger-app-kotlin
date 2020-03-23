package com.github.aoreshin.android.messenger.ui.data.remote.repository

import android.content.Context
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.vo.UserListVO
import com.github.aoreshin.android.messenger.ui.data.vo.UserVO
import com.github.aoreshin.android.messenger.ui.service.MessengerApiService
import io.reactivex.Observable

class UserRepositoryImpl(ctx: Context) : UserRepository {
    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findById(id: Long): Observable<UserVO> {
        return service.showUser(id, preferences.accessToken as String)
    }
    override fun all(): Observable<UserListVO> {
        return service.listUsers(preferences.accessToken as String)
    }
    override fun echoDetails(): Observable<UserVO> {
        return service.echoDetails(preferences.accessToken as String)
    }
}