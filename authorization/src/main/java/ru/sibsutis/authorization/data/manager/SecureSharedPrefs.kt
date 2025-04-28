package ru.sibsutis.authorization.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureSharedPrefs(private val context: Context) {

    companion object {
        private const val PREFS_FILE_NAME = "secure_prefs"
        private const val KEY_ALIAS = "key_alias"
    }

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

    fun encryptData(data: String, key: String) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.edit {
            putString(key, data)
        }
    }

    fun decryptData(key: String): String? {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(key, null)
    }
}