package com.josenaves.iddog.data

import com.josenaves.iddog.data.remote.AuthRemoteDatasource

class AuthRepository(private val remote: AuthRemoteDatasource) : AuthDatasource {

    override suspend fun auth(email: String): String {
        return remote.auth(email)
    }
}