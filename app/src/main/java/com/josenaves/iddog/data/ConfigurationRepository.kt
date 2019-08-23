package com.josenaves.iddog.data

import com.josenaves.iddog.data.local.ConfigurationLocalDatasource

class ConfigurationRepository(private val local: ConfigurationLocalDatasource) : ConfigurationDatasource {

    val TOKEN = "token"

    override suspend fun insert(key: String, value: String) {
        local.insert(key, value)
    }

    override suspend fun update(key: String, value: String) {
        local.update(key, value)
    }

    override suspend fun delete(key: String) {
        local.delete(key)
    }

    override suspend fun get(key: String): String {
        return local.get(key)
    }
}