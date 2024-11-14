package com.zhadko.priceviewer.data.dataSource.dataStore

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {

    fun getToken(): Flow<String>
    suspend fun saveToken(token: String)
}