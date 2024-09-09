package com.mediaan.mymediaan.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsUtil {
    private val SHARED_PREFS_NAME = "MyMediaanAppPrefs"
    private val LOGGED_IN_ID_KEY = "logged_in_user"

    fun saveLoggedInUserId(context: Context, id: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LOGGED_IN_ID_KEY, id)
        editor.apply()
    }

    fun getLoggedInUserId(context: Context): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(LOGGED_IN_ID_KEY, "") ?: ""
    }
}