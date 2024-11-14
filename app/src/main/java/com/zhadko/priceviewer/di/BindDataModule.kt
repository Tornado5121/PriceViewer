package com.zhadko.priceviewer.di

import com.zhadko.priceviewer.data.dataSource.dataStore.SettingsDataStore
import com.zhadko.priceviewer.data.dataSource.dataStore.SettingsDataStoreImpl
import com.zhadko.priceviewer.data.dataSource.socket.SocketConnection
import com.zhadko.priceviewer.data.dataSource.socket.SocketConnectionImpl
import com.zhadko.priceviewer.data.repositories.AuthRepositoryImpl
import com.zhadko.priceviewer.data.repositories.FinancialRepositoryImpl
import com.zhadko.priceviewer.domain.repository.AuthRepository
import com.zhadko.priceviewer.domain.repository.FinancialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDataModule {

    @Binds
    abstract fun bindSocketConnection(socketConnection: SocketConnectionImpl): SocketConnection

    @Binds
    abstract fun bindFinancialRepository(financialRepository: FinancialRepositoryImpl): FinancialRepository

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindSettingsDataStore(settingsDataStore: SettingsDataStoreImpl): SettingsDataStore
}