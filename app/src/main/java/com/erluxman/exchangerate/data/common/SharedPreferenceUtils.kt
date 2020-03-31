package com.erluxman.exchangerate.data.common

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefs.edit()

    fun setValue(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.commit()
    }

    fun getFloatValue(key: String, defaultValue: Float = 0.0f): Float? {
        return prefs.getFloat(key, defaultValue)
    }

    fun removeKey(key: String) {
        editor.remove(key)
        editor.commit()
    }

    fun clear() {
        editor.clear().commit()
    }
}