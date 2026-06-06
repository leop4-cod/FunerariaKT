package com.funeraria.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.funeraria.app.data.local.TokenDataStore
import com.funeraria.app.domain.model.LoggedUser
import com.funeraria.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthUiState {
    data object Idle    : AuthUiState()
    data object Loading : AuthUiState()
    data class  Success(val user: LoggedUser) : AuthUiState()
    data class  Error(val message: String)    : AuthUiState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<LoggedUser?>(null)
    val currentUser: StateFlow<LoggedUser?> = _currentUser.asStateFlow()

    val isAuthenticated: StateFlow<Boolean> = _currentUser
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isAdmin: StateFlow<Boolean> = _currentUser
        .map { it?.isStaff == true }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    private val _isCheckingSession = MutableStateFlow(true)
    val isCheckingSession: StateFlow<Boolean> = _isCheckingSession.asStateFlow()

    init { restoreSession() }

    private fun restoreSession() {
        viewModelScope.launch {
            try {
                val snapshot = authRepository.getStoredUser()
                if (snapshot != null && authRepository.isLoggedIn()) {
                    _currentUser.value = LoggedUser(snapshot.id, snapshot.username, snapshot.isStaff)
                }
            } finally {
                _isCheckingSession.value = false
            }
        }
    }

    fun login(username: String, password: String) {
        if (_uiState.value is AuthUiState.Loading) return
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            authRepository.login(username.trim(), password)
                .onSuccess { user ->
                    _currentUser.value = user
                    _uiState.value     = AuthUiState.Success(user)
                }
                .onFailure { e ->
                    _uiState.value = AuthUiState.Error(e.message ?: "Error al iniciar sesión")
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _currentUser.value = null
            _uiState.value     = AuthUiState.Idle
        }
    }

    fun clearError() {
        if (_uiState.value is AuthUiState.Error) _uiState.value = AuthUiState.Idle
    }
}
