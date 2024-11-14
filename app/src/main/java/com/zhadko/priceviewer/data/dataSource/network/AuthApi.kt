package com.zhadko.priceviewer.data.dataSource.network

import com.zhadko.priceviewer.data.dataSource.dto.auth.AuthDto
import com.zhadko.priceviewer.data.dataSource.dto.auth.AuthRequestDto
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @FormUrlEncoded
    @POST("identity/realms/{realm}/protocol/openid-connect/token")
    suspend fun login(
        @Path("realm") realm: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
        @Field("client_id") clientId: String = "app-cli"
    ): AuthDto
}