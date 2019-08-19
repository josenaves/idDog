package com.josenaves.iddog.data.remote.api

import com.josenaves.iddog.data.remote.api.dto.AuthRequest
import com.josenaves.iddog.data.remote.api.dto.AuthResponse
import com.josenaves.iddog.data.remote.api.dto.FeedResponse
import retrofit2.Response
import retrofit2.http.*

const val HUSKY = "husky"
const val HOUND = "hound"
const val PUG = "pug"
const val LABRADOR = "labrador"

interface DogApi {

    @POST("signup")
    @Headers("Content-Type: application/json")
    suspend fun auth(@Body authRequest: AuthRequest) : Response<AuthResponse>

    @GET("feed")
    @Headers("Content-Type: application/json")
    suspend fun getDogs(@Query("category") category: String? = HUSKY) : Response<FeedResponse>
}