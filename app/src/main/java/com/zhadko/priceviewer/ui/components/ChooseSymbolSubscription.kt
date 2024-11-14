package com.zhadko.priceviewer.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhadko.priceviewer.domain.model.InstrumentModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseSymbolSubscription(
    modifier: Modifier = Modifier,
    optionsList: List<InstrumentModel>,
    subscribePrice: (InstrumentModel) -> Unit,
) {
    var expandedState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(optionsList[0]) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            expanded = expandedState,
            onExpandedChange = { expandedState = !expandedState }
        ) {
            TextField(
                value = selectedOption.symbol,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) },
                readOnly = true,
                textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandedState,
                onDismissRequest = { expandedState = false }
            ) {
                optionsList.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption = option
                            expandedState = false
                        },
                        text = { Text(text = option.symbol) },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { subscribePrice(selectedOption) }) {
            Text(text = "Subscribe")
        }
    }
}
