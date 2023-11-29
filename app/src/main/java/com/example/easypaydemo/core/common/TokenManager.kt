package com.example.easypaydemo.core.common

import android.content.Context
import com.example.easypaydemo.core.common.Constants.PREFS_TOKEN
import com.example.easypaydemo.core.common.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 29.11.2023
 */

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

   private var pref = context.getSharedPreferences(PREFS_TOKEN, Context.MODE_PRIVATE)

   fun saveToken(token: String) {
      val editor = pref.edit()
      editor.putString(USER_TOKEN, token)
      editor.apply()
   }

   fun getToken(): String? {
      return pref.getString(USER_TOKEN, null)
   }
}