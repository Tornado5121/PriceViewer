package com.zhadko.priceviewer.data.dataSource.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("not-before-policy")
    val notBeforePolicy: Int,
    @SerializedName("refresh_expires_in")
    val refreshExpiresIn: Int,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("session_state")
    val sessionState: String,
    @SerializedName("token_type")
    val tokenType: String,
)