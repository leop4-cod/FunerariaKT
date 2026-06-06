package com.funeraria.app.data.remote.api

import com.funeraria.app.data.remote.dto.TokenRefreshRequest
import com.funeraria.app.data.remote.dto.TokenRequest
import com.funeraria.app.data.remote.dto.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("token/")
    suspend fun login(@Body request: TokenRequest): Response<TokenResponse>

    @POST("token/refresh/")
    suspend fun refreshToken(@Body request: TokenRefreshRequest): Response<TokenResponse>
}
