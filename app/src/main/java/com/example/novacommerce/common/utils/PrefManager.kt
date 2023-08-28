package com.example.novacommerce.common.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class PrefManager(context: Context) {

    private var masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private var sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val editor = sharedPreferences.edit()

    var uid: String
        set(value) { editor.putString("uid", value).apply() }
        get() { return sharedPreferences.getString("uid", "")!! }

    var username: String
        set(value) { editor.putString("username", value).apply() }
        get() { return sharedPreferences.getString("username", "")!! }
}