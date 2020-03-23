package com.github.aoreshin.android.messenger.ui.auth

import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.vo.UserVO

interface AuthInteractor {
    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String

    interface onAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    fun persistAccessToken(preferences: AppPreferences)

    fun persistUserDetails(preferences: AppPreferences)
}