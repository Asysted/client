package com.mvnh.asysted.domain.repository

import com.mvnh.asysted.domain.model.AuthTokens
import com.mvnh.asysted.domain.model.InviteCode
import com.mvnh.asysted.domain.model.TempToken
import com.mvnh.asysted.domain.model.UserCredentials

interface AuthRepository {
    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken>
    suspend fun login(credentials: UserCredentials): Result<AuthTokens>
}