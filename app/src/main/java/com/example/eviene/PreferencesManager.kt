package com.example.eviene

import android.content.Context
import android.content.SharedPreferences

object PreferencesManager {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_TOKEN = "token"
    private const val KEY_USER_ID = "userId"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun saveUserId(context: Context, userId: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USER_ID, userId)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(KEY_TOKEN, null)
    }

    fun getUserId(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_ID, null)
    }

    fun clearToken(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
    }

    fun clearUserId(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(KEY_USER_ID)
        editor.apply()
    }
}

