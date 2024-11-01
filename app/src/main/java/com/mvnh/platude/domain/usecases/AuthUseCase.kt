package com.mvnh.platude.domain.usecases

import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken
import com.mvnh.platude.domain.repository.AuthRepository
import java.util.UUID

class AuthUseCase(private val repository: AuthRepository) {
    fun isValidInviteCode(inviteCode: String): Boolean {
        return runCatching { UUID.fromString(inviteCode) }.isSuccess && inviteCode.length == 36
    }

    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        if (inviteCode.code.isBlank()) {
            return Result.failure(IllegalArgumentException("Invite code cannot be empty"))
        }

        return repository.redeemInviteCode(inviteCode)
    }
}