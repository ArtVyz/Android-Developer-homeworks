package com.example.homework11

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "preference_name"
private const val KEY_STRING_NAME = "key_string"
class Repository {
    private var localVariable: String? = null
    private fun getDataFromSharedPreference(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(KEY_STRING_NAME,null)
    }
    private fun getDataFromLocalVariable(): String? {
        return localVariable?.ifEmpty {
            null
        }
    }
    fun saveText(context: Context,text: String) {
        localVariable = text
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(KEY_STRING_NAME, text)
        editor.apply()
    }
    fun clearText(context: Context) {
        val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
    fun getText(context: Context): String? {
        return if (localVariable != null) {
            getDataFromLocalVariable()
        } else {
            getDataFromSharedPreference(context)
        }
    }
}