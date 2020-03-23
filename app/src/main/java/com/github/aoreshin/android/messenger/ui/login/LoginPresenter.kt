package com.github.aoreshin.android.messenger.ui.login

interface LoginPresenter {
    fun executeLogin(username: String, password: String)
}