package com.mvnh.asysted.data.network.service

import com.mvnh.asysted.data.mapper.toDomainModel
import com.mvnh.asysted.data.mapper.toDto
import com.mvnh.asysted.data.network.dto.AuthTokensDto
import com.mvnh.asysted.data.network.dto.TempTokenDto
import com.mvnh.asysted.domain.model.AuthTokens
import com.mvnh.asysted.data.network.dto.BasicApiResponse
import com.mvnh.asysted.domain.model.InviteCode
import com.mvnh.asysted.domain.model.TempToken
import com.mvnh.asysted.domain.model.UserCredentials
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthService(private val client: HttpClient) {
    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        return try {
            val response: BasicApiResponse<TempTokenDto> = client.post("/auth/invite/redeem") {
                contentType(ContentType.Application.Json)
                setBody(inviteCode.toDto())
            }.body()

            if (response.success) {
                response.data?.let { Result.success(it.toDomainModel()) }
                    ?: Result.failure(Exception(response.message))
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(credentials: UserCredentials): Result<AuthTokens> {
        return try {
            val response: BasicApiResponse<AuthTokensDto> = client.post("/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(credentials.toDto())
            }.body()

            if (response.success) {
                response.data?.let { Result.success(it.toDomainModel()) }
                    ?: Result.failure(Exception(response.message))
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
