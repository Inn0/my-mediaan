package com.mediaan.mymediaan.view.ftu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CreateProfileInputField(value: String, onChange: (String) -> Unit, minValue: Int, maxValue: Int, labelId: Int, isAge: Boolean = false) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (isAge || it.length <= maxValue) {
                onChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isAge) KeyboardType.Number else KeyboardType.Text
        ),
        label = { Text(stringResource(id = labelId)) },
        modifier = Modifier.fillMaxWidth(),
        supportingText = {
            Text(
                text = if (isAge) {"min $minValue y/o"} else {"min $minValue char., ${value.length} / $maxValue"},
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        isError = when {
            isAge -> {
                val age = value.toIntOrNull()
                value.isNotEmpty() && (age == null || age < minValue || age > maxValue)
            }
            else -> value.isNotEmpty() && value.length < minValue
        }
    )
}