package com.zhadko.priceviewer.domain.model.auth

data class AuthUser(
    val token: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshExpiresIn: Int,
)
