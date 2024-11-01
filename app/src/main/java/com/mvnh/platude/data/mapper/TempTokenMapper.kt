package com.mvnh.platude.data.mapper

import com.mvnh.platude.data.network.dto.TempTokenDto
import com.mvnh.platude.domain.model.TempToken

fun TempTokenDto.toDomainModel() = TempToken(temporaryToken = temporaryToken)

fun TempToken.toDto() = TempTokenDto(temporaryToken = temporaryToken)