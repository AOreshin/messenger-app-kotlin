package com.github.aoreshin.android.messenger.ui.signup

import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences

interface SignUpPresenter {
    var preferences: AppPreferences

    fun executeSignUp(username: String, phoneNumber: String, password: String)
}