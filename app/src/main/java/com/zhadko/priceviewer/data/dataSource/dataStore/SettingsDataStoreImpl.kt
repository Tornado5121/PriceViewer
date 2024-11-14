package com.zhadko.priceviewer.data.dataSource.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : SettingsDataStore {

    companion object {
        private const val AUTH_TOKEN_KEY = "auth_token"
    }

    private val authTokenKey = stringPreferencesKey(AUTH_TOKEN_KEY)

    override fun getToken(): Flow<String> {
        return dataStore.data.map { preference -> preference[authTokenKey] ?: "" }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences -> preferences[authTokenKey] = token }
    }
}