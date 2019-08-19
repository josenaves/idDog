package com.josenaves.iddog.data.remote.api

import com.josenaves.iddog.data.remote.api.dto.AuthRequest
import com.josenaves.iddog.data.remote.api.dto.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DogApi {

    @POST("signup")
    @Headers("Content-Type: application/json")
    suspend fun auth(@Body authRequest: AuthRequest) : Response<AuthResponse>

    @GET("feed")
    @Headers("Content-Type: application/json")
    suspend fun getDogs() : Response<Any>
}