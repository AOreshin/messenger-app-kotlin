package com.github.aoreshin.android.messenger.ui.settings

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.Toast
import androidx.preference.EditTextPreference
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.remote.request.StatusUpdateRequestObject
import com.github.aoreshin.android.messenger.ui.service.MessengerApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileStatusPreference(context: Context, attributeSet: AttributeSet) : EditTextPreference(context, attributeSet) {
    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val preferences: AppPreferences = AppPreferences.create(context)

    fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(context, "Status cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val requestObject = StatusUpdateRequestObject(text)
                service.updateUserStatus(requestObject, preferences.accessToken as String)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res -> preferences.storeUserDetails(res) },
                        { error ->
                            Toast.makeText(context, "Unable to update status at the moment. Try again later.", Toast.LENGTH_LONG).show()
                            error.printStackTrace()})
            }
        }
    }
}