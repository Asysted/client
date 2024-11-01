package com.mvnh.platude.data.network.auth

import kotlinx.serialization.Serializable

@Serializable
data class TempTokenDto(val temporaryToken: String)