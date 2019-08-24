package com.josenaves.iddog.data.remote

import com.josenaves.iddog.data.AuthDatasource
import com.josenaves.iddog.data.remote.api.DogApi
import com.josenaves.iddog.data.remote.api.dto.AuthRequest
import java.io.IOException

class AuthRemoteDatasource(private val api: DogApi) : AuthDatasource {

    override suspend fun auth(email: String): String {
        val response =  api.auth(AuthRequest(email))
        if (response.isSuccessful) {
            return response.body()?.user?.token ?: ""
        }

        throw IOException("Error on auth")
    }
}