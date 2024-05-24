package com.example.coffeeordering.ui.profile

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val FULL_NAME = "full_name"
        private const val NIM = "nim"
        private const val PRODI = "prodi"
        private const val FAKULTAS = "fakultas"
        private const val UNIVERSITAS = "universitas"
        private const val EMAIL = "email"
    }
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setUser(value: UserModel) {
        val editor = preferences.edit()
        editor.putString(FULL_NAME, value.fullName)
        editor.putString(NIM, value.nim)
        editor.putString(PRODI, value.prodi)
        editor.putString(FAKULTAS, value.fakultas)
        editor.putString(UNIVERSITAS, value.universitas)
        editor.putString(EMAIL, value.email)
        editor.apply()
    }
    fun getUser(): UserModel {
        val model = UserModel()
        model.fullName = preferences.getString(FULL_NAME, "")
        model.nim = preferences.getString(NIM, "")
        model.prodi = preferences.getString(PRODI, "")
        model.fakultas = preferences.getString(FAKULTAS, "")
        model.universitas = preferences.getString(UNIVERSITAS, "")
        model.email = preferences.getString(EMAIL, "")
        return model
    }
}