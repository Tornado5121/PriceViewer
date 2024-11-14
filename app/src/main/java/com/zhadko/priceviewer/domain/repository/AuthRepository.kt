package com.zhadko.priceviewer.domain.repository

import com.zhadko.priceviewer.domain.model.auth.AuthUser

interface AuthRepository {

    suspend fun login(username: String, password: String): AuthUser
    suspend fun getToken(): String
}