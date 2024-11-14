package com.zhadko.priceviewer.data.dataSource.network

import com.zhadko.priceviewer.data.dataSource.dto.price.history.PriceHistoryDto
import com.zhadko.priceviewer.data.dataSource.dto.price.instruments.InstrumentsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PriceApi {

    @GET("api/bars/v1/bars/count-back")
    suspend fun getPriceHistoryList(
        @Query("instrumentId") instrumentId: String,
        @Query("provider") provider: String,
        @Query("interval") interval: Int,
        @Query("periodicity") periodicity: String,
        @Query("barsCount") barsCount: Int,
    ): PriceHistoryDto


    @GET("api/instruments/v1/instruments")
    suspend fun getInstrumentsList(
        @Query("provider") provider: String,
        @Query("kind") kind: String,
        @Query("symbol") symbol: Int? = null,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): InstrumentsDto
}