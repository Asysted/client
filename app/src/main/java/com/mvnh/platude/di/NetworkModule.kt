package com.mvnh.platude.di

import com.mvnh.platude.data.repository.AuthRepositoryImpl
import com.mvnh.platude.domain.repository.auth.AuthRepository
import com.mvnh.platude.domain.usecases.AuthUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
            install(DefaultRequest) {
                url("http://127.0.0.1:8080/")
            }
        }
    }

    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { AuthUseCase(get()) }
}