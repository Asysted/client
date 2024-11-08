package com.mvnh.asysted.data.repository

import com.mvnh.asysted.data.network.service.AuthService
import com.mvnh.asysted.domain.model.AuthTokens
import com.mvnh.asysted.domain.model.InviteCode
import com.mvnh.asysted.domain.model.TempToken
import com.mvnh.asysted.domain.model.UserCredentials
import com.mvnh.asysted.domain.repository.AuthRepository

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        return service.redeemInviteCode(inviteCode)
    }

    override suspend fun login(credentials: UserCredentials): Result<AuthTokens> {
        return service.login(credentials)
    }
}