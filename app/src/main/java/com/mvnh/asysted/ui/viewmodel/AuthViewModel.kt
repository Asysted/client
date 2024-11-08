package com.mvnh.asysted.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvnh.asysted.domain.usecases.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val useCase: AuthUseCase) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> get() = _authState

    init {
        checkInviteCodeTokenValidity()
    }

    fun redeemInviteCode(inviteCode: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = useCase.redeemInviteCode(inviteCode)

            _authState.value = when {
                result.isSuccess -> {
                    Log.d("Asysted", result.getOrThrow().toString())
                    onInviteCodeAccepted()
                    AuthState.Success(result.getOrThrow())
                }

                else -> {
                    Log.e("Asysted", result.exceptionOrNull()?.message ?: "An error occurred")
                    AuthState.Error(result.exceptionOrNull()?.message ?: "An error occurred")
                }
            }
        }
    }

    private fun onInviteCodeAccepted() {
        val currentTime = System.currentTimeMillis()
        useCase.saveInviteCodeAcceptedTimestamp(currentTime)
    }

    private fun checkInviteCodeTokenValidity() {
        val currentTime = System.currentTimeMillis()
        val inviteCodeAcceptedTimestamp = useCase.getInviteCodeAcceptedTimestamp()

        _authState.value = if (currentTime - inviteCodeAcceptedTimestamp < 1 * 60 * 1000L) {
            Log.d("Asysted", "Invite code is still valid: current time is $currentTime, invite code accepted at $inviteCodeAcceptedTimestamp")
            AuthState.InviteCodeValid
        } else {
            Log.e("Asysted", "Invite code expired")
            AuthState.InviteCodeExpired
        }
    }

    fun signIn(username: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = useCase.login(username, password)

            _authState.value = when {
                result.isSuccess -> {
                    Log.d("Asysted", result.getOrThrow().toString())
                    AuthState.Success(result.getOrThrow())
                }

                else -> {
                    Log.e("Asysted", result.exceptionOrNull()?.message ?: "An error occurred")
                    AuthState.Error(result.exceptionOrNull()?.message ?: "An error occurred")
                }
            }
        }
    }
}

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success(val data: Any?) : AuthState()
    data class Error(val message: String) : AuthState()
    data object InviteCodeValid : AuthState()
    data object InviteCodeExpired : AuthState()
}