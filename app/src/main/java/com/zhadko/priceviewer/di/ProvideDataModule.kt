package com.zhadko.priceviewer.di

import android.content.Context
import com.zhadko.priceviewer.data.dataSource.dataStore.dataStorePreferences
import com.zhadko.priceviewer.data.dataSource.network.AuthApi
import com.zhadko.priceviewer.data.dataSource.network.PriceApi
import com.zhadko.priceviewer.data.dataSource.network.interceptors.HeaderInterceptor
import com.zhadko.priceviewer.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ProvideDataModule {

    @Provides
    fun providePriceApi(okHttpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl("https://platform.fintacharts.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(PriceApi::class.java)

    @Provides
    fun provideAuthApi() =
        Retrofit.Builder().baseUrl("https://platform.fintacharts.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AuthApi::class.java)

    @Provides
    fun provideInterceptor(authRepository: AuthRepository): Interceptor {
        return HeaderInterceptor(authRepository)
    }

    @Provides
    fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = context.dataStorePreferences

    @Provides
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}