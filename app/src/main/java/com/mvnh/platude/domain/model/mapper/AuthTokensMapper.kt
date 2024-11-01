package com.mvnh.platude.domain.model.mapper

import com.mvnh.platude.data.network.dto.AuthTokensDto
import com.mvnh.platude.domain.model.AuthTokens

fun AuthTokensDto.toDomainModel(): AuthTokens =
    AuthTokens(accessToken = this.accessToken, refreshToken = this.refreshToken)

fun AuthTokens.toDto(): AuthTokensDto =
    AuthTokensDto(accessToken = this.accessToken, refreshToken = this.refreshToken)