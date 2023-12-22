package fr.onat68.ailerons_app_android.screens.context

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import fr.onat68.ailerons_app_android.R
import fr.onat68.ailerons_app_android.UIElements.DatePickerUI
import fr.onat68.ailerons_app_android.UIElements.ModifierUI
import fr.onat68.ailerons_app_android.formaters.DateFormatter
import fr.onat68.ailerons_app_android.formaters.LocationFormatter
import java.util.Date

val fieldModifier = ModifierUI.fieldModifier

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    onDateChange: (Long) -> Unit,
    date: Long
) {
    Text(LocalContext.current.resources.getString(R.string.date_field))
    val formatedDate = DateFormatter(Date(date))

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    TextField(
        value = formatedDate.ddMMYYYY,
        onValueChange = { },
        enabled = false,
        modifier = fieldModifier
            .clickable(onClick = { showDatePicker = true }),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.White
        ),
        readOnly = true
    )

    if (showDatePicker) {

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date,
            initialDisplayMode = DisplayMode.Input,
            selectableDates = formatedDate.selectableDates
        )
        DatePickerDialog(
            colors = DatePickerUI.CustomDatePickerColors(),
            onDismissRequest = { showDatePicker = false },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(LocalContext.current.resources.getString(R.string.dismiss))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onDateChange(datePickerState.selectedDateMillis!!)
                    showDatePicker = false
                }) {
                    Text(LocalContext.current.resources.getString(R.string.confirm))
                }
            }

        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourField(
    onTimeChange: (hour: Int, min: Int) -> Unit,
    hour: Int,
    min: Int
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    val timePickerState = rememberTimePickerState(
        initialHour = hour,
        initialMinute = min
    )
    timePickerState.hour
    Text(LocalContext.current.resources.getString(R.string.hour_field))
    TextField(
        value = "$hour:$min",
        onValueChange = { },
        enabled = false,
        modifier = fieldModifier
            .clickable(onClick = { showDialog = true }),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.White
        ),
        readOnly = true
    )

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 12.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f)
                    )
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(state = timePickerState, colors = TimePickerDefaults.colors(Color.Black))
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDialog = false }) {
                        Text(LocalContext.current.resources.getString(R.string.dismiss))
                    }
                    TextButton(
                        onClick = {
                            showDialog = false
                            onTimeChange(timePickerState.hour, timePickerState.minute)
                        }
                    ) {
                        Text(LocalContext.current.resources.getString(R.string.confirm))
                    }
                }
            }
        }
    }
}


@Composable
fun LocationField(getLastLocation: () -> Unit, location: LatLng, navigate: (String) -> Unit) {
    val stringFormatedLocation = LocationFormatter(location, 5)
    Text(LocalContext.current.resources.getString(R.string.location_field))
    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = "Latitude: ${stringFormatedLocation.latitude}, Longitude: ${stringFormatedLocation.longitude}",
            onValueChange = { },
            enabled = false,
            modifier = fieldModifier
                .clickable(onClick = { navigate("mapView") }),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.White
            )
        )
        IconButton(onClick = getLastLocation, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Location",
                tint = Color.White
            )
        }
    }

}
@Composable
fun DepthField(
    onDepthChange: (String) -> Unit,
    depth: String
) {
    Text(LocalContext.current.resources.getString(R.string.depth_field))
    TextField(
        value = depth,
        onValueChange = { onDepthChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Text("0") },
        suffix = { Text(LocalContext.current.resources.getString(R.string.meters)) },
        modifier = fieldModifier
    )
}
@Composable
fun SituationField(
    onSituationChange: (String) -> Unit,
    selectedSituation: String
) {
    val situationList = LocalContext.current.resources.getStringArray(R.array.situation_field_array)
    var expanded by remember { mutableStateOf(false) }
    Box {
        Column {
            Text(LocalContext.current.resources.getString(R.string.situation_field))
            TextField(
                value = (selectedSituation),
                onValueChange = { },
                modifier = fieldModifier,
                trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                readOnly = true
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                situationList.forEach { entry ->

                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onSituationChange(entry)
                            expanded = false
                        },
                        text = {
                            Text(
                                text = (entry),
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .align(Alignment.Start)
                            )
                        }
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { expanded = !expanded }
                )
        )
    }
}



