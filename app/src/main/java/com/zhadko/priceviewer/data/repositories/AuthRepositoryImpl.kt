package com.zhadko.priceviewer.data.repositories

import com.zhadko.priceviewer.data.dataSource.dataStore.SettingsDataStore
import com.zhadko.priceviewer.data.dataSource.network.AuthApi
import com.zhadko.priceviewer.domain.mappers.auth.toDomain
import com.zhadko.priceviewer.domain.model.auth.AuthUser
import com.zhadko.priceviewer.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val settingsDataStore: SettingsDataStore,
    private val coroutineScope: CoroutineScope,
) : AuthRepository {

    override suspend fun login(username: String, password: String): AuthUser {
        return withContext(Dispatchers.IO) {
            val user = authApi.login("fintatech", username = username, password = password)
                .toDomain()
            settingsDataStore.saveToken(user.token)
            user
        }
    }

    override suspend fun getToken(): String {
        return settingsDataStore.getToken().first()
    }
}