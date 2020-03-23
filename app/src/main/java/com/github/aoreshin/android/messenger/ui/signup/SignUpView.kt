package com.github.aoreshin.android.messenger.ui.signup

import com.github.aoreshin.android.messenger.ui.auth.AuthView
import com.github.aoreshin.android.messenger.ui.base.BaseView

interface SignUpView : BaseView, AuthView {
    fun showProgress()
    fun showSignUpError()
    fun hideProgress()
    fun setUsernameError()
    fun setPhoneNumberError()
    fun setPasswordError()
    fun navigateToHome()
}