package com.zhadko.priceviewer.data.dataSource.dto.auth

data class AuthRequestDto(
    val grant_type: String = "password",
    val client_id: String = "app-cli",
    val username: String,
    val password: String,
)
