package com.mvnh.asysted.data.mapper

import com.mvnh.asysted.data.network.dto.UserCredentialsDto
import com.mvnh.asysted.domain.model.UserCredentials

fun UserCredentialsDto.toDomainModel() = UserCredentials(username, password)

fun UserCredentials.toDto() = UserCredentialsDto(username, password)