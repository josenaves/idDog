package com.josenaves.iddog.data

interface ConfigurationDatasource {
    suspend fun insert(key: String, value: String)
    suspend fun update(key: String, value: String)
    suspend fun delete(key: String)
    suspend fun get(key: String): String
}