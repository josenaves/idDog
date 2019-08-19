package com.josenaves.iddog.data.remote.api.dto

data class AuthResponse(val user: User)

data class User(val email: String, val token: String)