package com.mvnh.platude.domain.usecases

import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken
import com.mvnh.platude.domain.repository.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        return repository.redeemInviteCode(inviteCode)
    }
}