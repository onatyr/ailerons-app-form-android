package fr.onat68.ailerons_app_android.screens.context

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
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import fr.onat68.ailerons_app_android.R
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    onDateChange: (Long) -> Unit,
    date: Long
) {
    Text(LocalContext.current.resources.getString(R.string.date_field))

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    TextField(
        value = Date(date).toString(),
        onValueChange = { },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
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
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= System.currentTimeMillis()
                }
                override fun isSelectableYear(year: Int): Boolean {
                    return year <= Date().year + 1900
                }
            }
        )
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                containerColor = Color.Black,
                titleContentColor = Color.Black,
                headlineContentColor = Color.Black,
                weekdayContentColor = Color.Black,
                subheadContentColor = Color.Black,
                yearContentColor = Color.Black,
                currentYearContentColor = Color.Black,
                selectedYearContentColor = Color.Black,
                selectedYearContainerColor = Color.Black,
                dayContentColor = Color.Black,
                disabledDayContentColor = Color.Black,
                selectedDayContentColor = Color.Black,
                disabledSelectedDayContentColor = Color.Black,
                selectedDayContainerColor = Color.Black,
                disabledSelectedDayContainerColor = Color.Black,
                todayContentColor = Color.Black,
                todayDateBorderColor = Color.Black,
            ),
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { showDialog = true }),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.White
        ),
        readOnly = true
    )

    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 12.dp)
                ),
            onDismissRequest = { showDialog = false }
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
                TimePicker(state = timePickerState)

                // buttons
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
fun LocationField(location: LatLng, navigate: (String) -> Unit) {
    Text(LocalContext.current.resources.getString(R.string.location_field))
    TextField(
        value = "Latitude: ${location.latitude}, Longitude: ${location.longitude}",
        onValueChange = { },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { navigate("mapView") }),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.White
        )
    )
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
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DepthFieldPreview() {
    Text("Hey")
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
                modifier = Modifier.fillMaxWidth(),
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

