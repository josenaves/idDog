package com.josenaves.iddog.data.remote.api

import android.util.Log
import com.josenaves.iddog.BuildConfig
import com.josenaves.iddog.data.remote.api.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogApiClient {

    companion object {

        private const val URL = "https://api-iddog.idwall.co/"

        fun create(): DogApi {

            val logger = HttpLoggingInterceptor()

            logger.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(AuthInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogApi::class.java)
        }
    }
}