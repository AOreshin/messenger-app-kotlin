package com.github.aoreshin.android.messenger.ui.data.remote.repository

import com.github.aoreshin.android.messenger.ui.data.vo.UserListVO
import com.github.aoreshin.android.messenger.ui.data.vo.UserVO
import io.reactivex.Observable

interface UserRepository {
    fun findById(id: Long): Observable<UserVO>
    fun all(): Observable<UserListVO>
    fun echoDetails(): Observable<UserVO>
}