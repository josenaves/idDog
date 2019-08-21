package com.josenaves.iddog.data.remote.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (!originalRequest.url.toString().contains("/feed")) return chain.proceed(originalRequest)

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWQ1YWVmNTY0NzRkYWE3ZjBmYjIwMTc2IiwiaWF0IjoxNTY2MjQwNTk4LCJleHAiOjE1Njc1MzY1OTh9.Mrr-4OZTddM8CrwdBHwXHmoj9sCoLJps904nX1lia9Q"   // get token logic

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", token)
            .build()

        return chain.proceed(newRequest)
    }
}