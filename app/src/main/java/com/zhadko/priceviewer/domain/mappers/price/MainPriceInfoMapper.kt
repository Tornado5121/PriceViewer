package com.zhadko.priceviewer.domain.mappers.price

import com.zhadko.priceviewer.data.dataSource.dto.socketDto.PriceMainInfoDto
import com.zhadko.priceviewer.domain.model.PriceMainInfoModel

fun PriceMainInfoDto.toDomain(): PriceMainInfoModel {
    return PriceMainInfoModel(
        symbol = (this.last?.volume ?: 0).toString(),
        price = this.last?.price ?: 0.0,
        time = this.last?.timestamp ?: ""
    )
}

fun List<PriceMainInfoDto>.toDomain() = this.map { it.toDomain() }