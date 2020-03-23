package com.github.aoreshin.android.messenger.ui.signup

import android.text.TextUtils
import com.github.aoreshin.android.messenger.ui.auth.AuthInteractor
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.remote.request.LoginRequestObject
import com.github.aoreshin.android.messenger.ui.data.remote.request.UserRequestObject
import com.github.aoreshin.android.messenger.ui.data.vo.UserVO
import com.github.aoreshin.android.messenger.ui.service.MessengerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpInteractorImpl : SignUpInteractor {
    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String


    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun signUp(username: String,
                        phoneNumber: String, password: String,
                        listener: SignUpInteractor.OnSignUpFinishedListener){
        submittedUsername = username
        submittedPassword = password
        val userRequestObject = UserRequestObject(username, password, phoneNumber)

        when {
            TextUtils.isEmpty(username) -> listener.onUsernameError()
            TextUtils.isEmpty(phoneNumber) -> listener.onPhoneNumberError()
            TextUtils.isEmpty(password) -> listener.onPasswordError()
            else -> {
                service.createUser(userRequestObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res ->
                        userDetails = res
                        listener.onSuccess()
                    }, { error ->
                        listener.onError()
                        error.printStackTrace()
                    })
            }
        }
    }

    override fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener) {
        val userRequestObject = LoginRequestObject(submittedUsername, submittedPassword)

        service.login(userRequestObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { res ->
                accessToken = res.headers()["Authorization"] as String
                listener.onAuthSuccess()
            }, { error ->
                listener.onAuthError()
                error.printStackTrace()
            })
    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }
    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }
}