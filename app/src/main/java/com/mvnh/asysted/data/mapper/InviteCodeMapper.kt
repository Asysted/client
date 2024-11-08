package com.mvnh.asysted.data.mapper

import com.mvnh.asysted.data.network.dto.InviteCodeDto
import com.mvnh.asysted.domain.model.InviteCode

fun InviteCodeDto.toDomainModel(): InviteCode = InviteCode(this.inviteCode)

fun InviteCode.toDto(): InviteCodeDto = InviteCodeDto(this.code)