package com.mvnh.asysted.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)