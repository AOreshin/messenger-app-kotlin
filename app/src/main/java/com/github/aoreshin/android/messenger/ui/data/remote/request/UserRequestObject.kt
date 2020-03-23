package com.github.aoreshin.android.messenger.ui.data.remote.request

data class UserRequestObject(
    val username: String,
    val password: String,
    val phoneNumber: String = ""
)