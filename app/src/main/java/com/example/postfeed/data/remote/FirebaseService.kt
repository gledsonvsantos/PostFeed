package com.example.postfeed.data.remote

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

object FirebaseService {

    fun logEvent(context: Context, eventName: String, params: Bundle) {
        FirebaseAnalytics.getInstance(context).logEvent(eventName, params)
    }

    fun logCustomEvent(analytics: FirebaseAnalytics, eventName: String, params: Bundle) {
        analytics.logEvent(eventName, params)
    }

    fun reportCrash(message: String, throwable: Throwable) {
        FirebaseCrashlytics.getInstance().apply {
            log(message)
            recordException(throwable)
        }
    }
}