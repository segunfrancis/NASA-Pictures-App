package com.project.segunfrancis.local.db

import android.content.Context
import android.content.SharedPreferences
import com.project.segunfrancis.local.util.LocalConstants.ON_BOARDING_KEY
import com.project.segunfrancis.local.util.LocalConstants.SHARED_PREF_KEY
import javax.inject.Inject

class PreferenceHelper @Inject constructor(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    fun hasUserSeenOnBoarding(): Boolean {
        return pref.getBoolean(ON_BOARDING_KEY, false)
    }

    fun setUserHasSeenOnBoarding(value: Boolean) {
        pref.edit().putBoolean(ON_BOARDING_KEY, value).apply()
    }

    fun isBookmarked(date: String): Boolean {
        return pref.getBoolean(date, false)
    }

    fun addBookmarkToPref(date: String) {
        pref.edit().putBoolean(date, true).apply()
    }

    fun removeBookmarkFromPref(date: String) {
        pref.edit().putBoolean(date, false).apply()
    }
}