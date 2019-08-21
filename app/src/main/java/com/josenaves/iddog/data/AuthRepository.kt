package com.josenaves.iddog.data

class AuthRepository(private val remote: AuthDatasource) : AuthDatasource {

    override suspend fun auth(email: String): String {
        return remote.auth(email)
    }
}