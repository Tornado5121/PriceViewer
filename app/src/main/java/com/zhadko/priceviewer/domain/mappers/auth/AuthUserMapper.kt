package com.zhadko.priceviewer.domain.mappers.auth

import com.zhadko.priceviewer.data.dataSource.dto.auth.AuthDto
import com.zhadko.priceviewer.domain.model.auth.AuthUser

fun AuthDto.toDomain(): AuthUser {
    return AuthUser(
        token = this.accessToken,
        expiresIn = this.expiresIn,
        refreshToken = this.refreshToken,
        refreshExpiresIn = this.refreshExpiresIn
    )
}