package com.example.focusclock.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.focusclock.presentation.activity.MainActivity

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(MainActivity.PREF_NAME,MODE_PRIVATE)


}