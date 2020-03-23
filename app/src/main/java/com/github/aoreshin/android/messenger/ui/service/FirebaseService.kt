package com.github.aoreshin.android.messenger.ui.service

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.github.aoreshin.android.messenger.R
import com.github.aoreshin.android.messenger.ui.data.remote.request.TokenUpdateRequestObject
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseService(val messengerApiService: MessengerApiService) : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        messengerApiService.updateUserToken(TokenUpdateRequestObject(mutableSetOf(token)))
    }
}