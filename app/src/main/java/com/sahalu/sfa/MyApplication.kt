package com.sahalu.sfa

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs


class MyApplication : Application() {


    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}