package com.mvnh.asysted.domain.usecases

import android.content.SharedPreferences
import com.mvnh.asysted.domain.model.AuthTokens
import com.mvnh.asysted.domain.model.InviteCode
import com.mvnh.asysted.domain.model.TempToken
import com.mvnh.asysted.domain.model.UserCredentials
import com.mvnh.asysted.domain.repository.AuthRepository
import java.util.UUID

class AuthUseCase(
    private val repository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) {
    private fun isValidInviteCode(inviteCode: String): Boolean {
        return runCatching { UUID.fromString(inviteCode) }.isSuccess && inviteCode.length == 36
    }

    private fun isValidUsername(username: String): Boolean {
        return username.length in 4..32
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length in 8..64
    }

    fun saveInviteCodeAcceptedTimestamp(timestamp: Long) {
        sharedPreferences.edit()
            .putLong(
                "invite_code_accepted_timestamp",
                timestamp
            )
            .apply()
    }

    fun getInviteCodeAcceptedTimestamp(): Long {
        return sharedPreferences.getLong("invite_code_accepted_timestamp", 0L)
    }

    suspend fun redeemInviteCode(inviteCode: String): Result<TempToken> {
        if (!isValidInviteCode(inviteCode)) {
            return Result.failure(IllegalArgumentException("Invalid invite code"))
        }

        return repository.redeemInviteCode(InviteCode(inviteCode))
    }

    suspend fun login(username: String, password: String): Result<AuthTokens> {
        if (!(isValidPassword(password) && isValidUsername(username))) {
            return Result.failure(IllegalArgumentException("Invalid username or password"))
        }

        return repository.login(UserCredentials(username, password))
    }
}