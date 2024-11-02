package com.mvnh.platude.data.network.service

import com.mvnh.platude.data.mapper.toDomainModel
import com.mvnh.platude.data.mapper.toDto
import com.mvnh.platude.data.network.dto.TempTokenDto
import com.mvnh.platude.domain.model.BasicApiResponse
import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.model.TempToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthService(private val client: HttpClient) {
    suspend fun redeemInviteCode(inviteCode: InviteCode): Result<TempToken> {
        return try {
            val response = client.post("/auth/invite/redeem") {
                contentType(ContentType.Application.Json)
                setBody(inviteCode.toDto())
            }

            if (response.status.value != 200) {
                return Result.failure(Exception(response.status.description))
            }

            val body = response.body<BasicApiResponse<TempTokenDto>>()

            if (body.success) {
                body.data?.let { Result.success(it.toDomainModel()) }
                    ?: Result.failure(Exception(body.message))
            } else {
                Result.failure(Exception(body.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
