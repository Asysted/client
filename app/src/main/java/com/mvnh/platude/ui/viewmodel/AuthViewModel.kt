package com.mvnh.platude.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvnh.platude.domain.model.InviteCode
import com.mvnh.platude.domain.usecases.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val useCase: AuthUseCase) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState>
        get() = _authState

    fun redeemInviteCode(inviteCode: String) {
        if (!useCase.isValidInviteCode(inviteCode)) {
            _authState.value = AuthState.Error("Invalid invite code")
            return
        }

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = useCase.redeemInviteCode(InviteCode(inviteCode))

            when {
                result.isSuccess -> {
                    _authState.value = AuthState.Success(result.getOrThrow())
                }
                result.isFailure -> {
                    _authState.value = AuthState.Error(result.exceptionOrNull()?.message ?: "An error occurred")
                }
            }
        }
    }
}

sealed class AuthState {
    data object Idle : AuthState()
    data object Loading : AuthState()
    data class Success<T>(val data: T?) : AuthState()
    data class Error(val message: String) : AuthState()
}