package com.zhadko.priceviewer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextWithPlaceHolder(modifier: Modifier = Modifier, placeholder: String, value: String) {
    Column {
        Text(text = placeholder)
        Text(text = value)
    }
}