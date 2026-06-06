package com.funeraria.app.data.repository

import com.funeraria.app.data.local.TokenDataStore
import com.funeraria.app.data.remote.api.AuthApi
import com.funeraria.app.data.remote.dto.TokenRequest
import com.funeraria.app.domain.model.LoggedUser
import com.funeraria.app.domain.repository.AuthRepository
import com.funeraria.app.util.JwtDecoder
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<LoggedUser> =
        runCatching {
            val response = api.login(TokenRequest(username, password))
            if (!response.isSuccessful) {
                val code = response.code()
                error(when (code) {
                    400  -> "Usuario o contraseña incorrectos."
                    401  -> "Credenciales inválidas."
                    else -> "Error $code al iniciar sesión."
                })
            }
            val body   = response.body() ?: error("Respuesta vacía del servidor")
            val claims = JwtDecoder.getClaims(body.access)
            val userId  = claims?.optInt("user_id", 0) ?: 0
            val isStaff = claims?.optBoolean("is_staff", false) ?: false
            val uname   = claims?.optString("username", username) ?: username

            tokenDataStore.saveTokens(body.access, body.refresh)
            tokenDataStore.saveUser(userId, uname, isStaff)
            LoggedUser(id = userId, username = uname, isStaff = isStaff)
        }

    override suspend fun logout(): Result<Unit> = runCatching {
        tokenDataStore.clearSession()
    }

    override suspend fun getStoredUser(): TokenDataStore.UserSnapshot? =
        tokenDataStore.userSnapshot.first()

    override suspend fun isLoggedIn(): Boolean =
        !tokenDataStore.getAccessToken().isNullOrBlank()
}
