package com.zhadko.priceviewer.domain.mappers.price

import com.zhadko.priceviewer.data.dataSource.dto.price.instruments.Data
import com.zhadko.priceviewer.data.dataSource.dto.price.instruments.InstrumentsDto
import com.zhadko.priceviewer.domain.model.InstrumentModel

fun Data.toDomain(): InstrumentModel {
    return InstrumentModel(
        id = this.id,
        symbol = this.symbol
    )
}

fun InstrumentsDto.toDomain(): List<InstrumentModel> {
    return this.data.map {
        it.toDomain()
    }
}