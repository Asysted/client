package com.mvnh.platude.domain.model.mapper

import com.mvnh.platude.data.network.auth.InviteCodeDto
import com.mvnh.platude.domain.model.InviteCode

fun InviteCodeDto.toDomainModel(): InviteCode = InviteCode(code = this.inviteCode)

fun InviteCode.toDto(): InviteCodeDto = InviteCodeDto(inviteCode = this.code)