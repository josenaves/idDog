package com.josenaves.iddog.data.local

import android.content.Context
import com.josenaves.iddog.data.ConfigurationDatasource

class ConfigurationLocalDatasource(private val context: Context): ConfigurationDatasource {

    private val preferencesFilename = "com.josenaves.iddog.data.preferences"
    private val sharedPreferences = context.getSharedPreferences(preferencesFilename, 0)

    override suspend fun insert(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override suspend fun update(key: String, value: String) {
        insert(key, value)
    }

    override suspend fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override suspend fun get(key: String): String {
        return sharedPreferences.getString(key, "")
    }
}