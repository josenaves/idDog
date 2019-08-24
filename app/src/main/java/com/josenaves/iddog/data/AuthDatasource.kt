package com.josenaves.iddog.data

interface AuthDatasource {
    suspend fun auth(email: String) : String
}