package com.mvnh.asysted.data.mapper

import com.mvnh.asysted.data.network.dto.TempTokenDto
import com.mvnh.asysted.domain.model.TempToken

fun TempTokenDto.toDomainModel() = TempToken(temporaryToken)

fun TempToken.toDto() = TempTokenDto(temporaryToken)