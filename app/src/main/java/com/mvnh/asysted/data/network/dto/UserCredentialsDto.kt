package com.mvnh.asysted.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialsDto(
    val username: String,
    val password: String
)