package com.funeraria.app.data.remote.interceptors

import com.funeraria.app.data.local.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path    = request.url.encodedPath

        // Skip auth endpoints
        if (path.endsWith("token/") || path.endsWith("token/refresh/")) {
            return chain.proceed(request)
        }

        val token = runBlocking { tokenDataStore.getAccessToken() }

        val newRequest = request.newBuilder().apply {
            token?.let { addHeader("Authorization", "Bearer $it") }
        }.build()

        return chain.proceed(newRequest)
    }
}
