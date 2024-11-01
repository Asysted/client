package com.mvnh.platude.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class BasicApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)