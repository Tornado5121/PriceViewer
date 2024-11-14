package com.zhadko.priceviewer.data.dataSource.network.interceptors

import com.zhadko.priceviewer.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val authRepository: AuthRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", "Bearer ${runBlocking { authRepository.getToken() }}")
                .build()
        )
    }
}