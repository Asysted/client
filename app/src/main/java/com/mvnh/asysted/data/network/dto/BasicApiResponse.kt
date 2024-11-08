package com.mvnh.asysted.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class BasicApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)