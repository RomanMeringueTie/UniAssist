package ru.sibsutis.authorization.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecureSharedPrefs {
    private const val PREFS_FILE_NAME = "secure_prefs"
    private const val KEY_ALIAS = "key_alias"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        val masterKeyAlias =
            MasterKey.Builder(context, KEY_ALIAS).setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        return EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun encryptData(data: String, key: String, context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(key, data)
        editor.apply()
    }

    fun decryptData(key: String, context: Context): String? {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(key, null)
    }
}