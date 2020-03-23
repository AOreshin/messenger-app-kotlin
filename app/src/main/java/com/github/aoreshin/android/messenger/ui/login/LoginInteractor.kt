package com.github.aoreshin.android.messenger.ui.login

import com.github.aoreshin.android.messenger.ui.auth.AuthInteractor
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences

interface LoginInteractor : AuthInteractor {
    interface OnDetailsRetrievalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String, listener: AuthInteractor.onAuthFinishedListener)

    fun retrieveDetails(preferences: AppPreferences, listener: OnDetailsRetrievalFinishedListener)
}