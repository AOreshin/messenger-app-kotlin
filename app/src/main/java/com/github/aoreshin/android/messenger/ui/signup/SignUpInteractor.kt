package com.github.aoreshin.android.messenger.ui.signup

import com.github.aoreshin.android.messenger.ui.auth.AuthInteractor

interface SignUpInteractor : AuthInteractor {
    interface OnSignUpFinishedListener {
        fun onSuccess()
        fun onUsernameError()
        fun onPasswordError()
        fun onPhoneNumberError()
        fun onError()
    }

    fun signUp(username: String, phoneNumber: String, password: String, listener: OnSignUpFinishedListener)

    fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener)
}