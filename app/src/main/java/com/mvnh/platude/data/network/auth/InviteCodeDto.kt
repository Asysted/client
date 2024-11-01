package com.mvnh.platude.data.network.auth

import kotlinx.serialization.Serializable

@Serializable
data class InviteCodeDto(val inviteCode: String)