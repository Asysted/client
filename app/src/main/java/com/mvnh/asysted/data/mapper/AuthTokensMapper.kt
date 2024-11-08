package com.mvnh.asysted.data.mapper

import com.mvnh.asysted.data.network.dto.AuthTokensDto
import com.mvnh.asysted.domain.model.AuthTokens

fun AuthTokensDto.toDomainModel(): AuthTokens =
    AuthTokens(this.accessToken, this.refreshToken)

fun AuthTokens.toDto(): AuthTokensDto =
    AuthTokensDto(this.accessToken, this.refreshToken)