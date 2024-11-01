package com.mvnh.platude.data.repository

import com.mvnh.platude.data.network.dto.TempTokenDto
import com.mvnh.platude.domain.model.BasicApiResponse
import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken
import com.mvnh.platude.domain.model.mapper.toDomainModel
import com.mvnh.platude.domain.model.mapper.toDto
import com.mvnh.platude.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepositoryImpl(private val client: HttpClient) : AuthRepository {
    override suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
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
}