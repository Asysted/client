package com.mvnh.asysted.di

import android.content.Context
import com.mvnh.asysted.data.network.service.AuthService
import com.mvnh.asysted.data.repository.AuthRepositoryImpl
import com.mvnh.asysted.domain.repository.AuthRepository
import com.mvnh.asysted.domain.usecases.AuthUseCase
import com.mvnh.asysted.ui.viewmodel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthService(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { AuthUseCase(get(), get()) }

    viewModel { AuthViewModel(get()) }
    single { androidContext().getSharedPreferences("asysted", Context.MODE_PRIVATE) }
}