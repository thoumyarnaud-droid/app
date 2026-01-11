package com.financeme.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.SecureRandom

object DbPassphrase {
    private const val PREFS = "financeme_secure_prefs"
    private const val KEY = "db_passphrase"

    /**
     * Returns a stable random passphrase, stored encrypted in SharedPreferences.
     * This is a pragmatic MVP approach for local-only encrypted storage.
     */
    fun getOrCreate(context: Context): CharArray {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val prefs = EncryptedSharedPreferences.create(
            context,
            PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val existing = prefs.getString(KEY, null)
        if (existing != null) return existing.toCharArray()

        val random = ByteArray(32).also { SecureRandom().nextBytes(it) }
        val asString = random.joinToString(separator = "") { b -> "%02x".format(b) }
        prefs.edit().putString(KEY, asString).apply()
        return asString.toCharArray()
    }
}

