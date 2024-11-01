package com.mvnh.platude.domain.repository

import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken

interface AuthRepository {
    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken>
}