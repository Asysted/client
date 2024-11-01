package com.mvnh.platude.data.repository

import com.mvnh.platude.data.network.service.AuthService
import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken
import com.mvnh.platude.domain.repository.AuthRepository

class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {
    override suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        return service.redeemInviteCode(inviteCode)
    }
}