package com.mvnh.platude.domain.model.mapper

import com.mvnh.platude.data.network.auth.TempTokenDto
import com.mvnh.platude.domain.model.TempToken

fun TempTokenDto.toDomainModel() = TempToken(temporaryToken = temporaryToken)

fun TempToken.toDto() = TempTokenDto(temporaryToken = temporaryToken)