package com.mvnh.platude.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)