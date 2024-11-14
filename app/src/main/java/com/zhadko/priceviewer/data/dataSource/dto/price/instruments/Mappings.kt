package com.zhadko.priceviewer.data.dataSource.dto.price.instruments

import com.google.gson.annotations.SerializedName

data class Mappings(
    @SerializedName("active-tick")
    val active_tick: ActiveTick,
    val dxfeed: Dxfeed,
    val oanda: Oanda,
    val simulation: Simulation
)