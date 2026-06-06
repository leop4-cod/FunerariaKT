package com.funeraria.app.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "funeraria_auth_prefs")

@Singleton
class TokenDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val ACCESS_TOKEN  = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val USER_ID       = intPreferencesKey("user_id")
        private val USERNAME      = stringPreferencesKey("username")
        private val IS_STAFF      = booleanPreferencesKey("is_staff")
    }

    val userSnapshot: Flow<UserSnapshot?> = context.dataStore.data.map { prefs ->
        val id       = prefs[USER_ID]
        val username = prefs[USERNAME]
        val isStaff  = prefs[IS_STAFF] ?: false
        if (id != null && username != null) UserSnapshot(id, username, isStaff)
        else null
    }

    suspend fun saveTokens(access: String, refresh: String) {
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN]  = access
            prefs[REFRESH_TOKEN] = refresh
        }
    }

    suspend fun saveUser(id: Int, username: String, isStaff: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID]  = id
            prefs[USERNAME] = username
            prefs[IS_STAFF] = isStaff
        }
    }

    suspend fun getAccessToken(): String?  = context.dataStore.data.map { it[ACCESS_TOKEN] }.first()
    suspend fun getRefreshToken(): String? = context.dataStore.data.map { it[REFRESH_TOKEN] }.first()

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }

    data class UserSnapshot(
        val id: Int,
        val username: String,
        val isStaff: Boolean
    )
}
