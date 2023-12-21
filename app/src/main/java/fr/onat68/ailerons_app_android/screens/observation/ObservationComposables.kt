package fr.onat68.ailerons_app_android.screens.observation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PickerButton(value: String, onClick: () -> Unit, selectedOption: String) {
    val selected by remember {
        mutableStateOf(value == selectedOption)
    }
    val textColor = if(selected) Color.White else Color.DarkGray

    Tab(selected = selected, onClick = onClick, modifier = Modifier
        .background(if(selected) Color.Blue else Color.White)
        ) {
        Text(value, color = textColor)
    }
}
@Composable
fun Picker(options: List<String>, onSelectionChange: (String) -> Unit, selectedOption: String ) {
    TabRow(options.lastIndex, Modifier .fillMaxWidth()) {
    options.forEach{option -> PickerButton(value = option,
        onClick = {
            onSelectionChange(option)
        }, selectedOption)}}
}

@Preview
@Composable
fun PreviewPicker() {
    val selectedOption = remember {
        mutableStateOf("inconnue")
    }
    Picker(listOf("a","b","c","inconnue"),fun (selection) {selectedOption.value = selection}, selectedOption.value)
}
