package com.github.aoreshin.android.messenger.ui.base

import android.content.Context

interface BaseView {
    fun bindViews()
    fun getContext(): Context
}