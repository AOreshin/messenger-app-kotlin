package com.github.aoreshin.android.messenger.ui.login

import com.github.aoreshin.android.messenger.ui.auth.AuthView
import com.github.aoreshin.android.messenger.ui.base.BaseView

interface LoginView : BaseView, AuthView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameError()
    fun setPasswordError()
    fun navigateToSignUp()
    fun navigateToHome()
}