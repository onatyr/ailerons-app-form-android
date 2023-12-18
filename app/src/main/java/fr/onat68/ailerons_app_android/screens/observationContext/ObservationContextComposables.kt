package fr.onat68.ailerons_app_android.screens.observationContext

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
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import fr.onat68.ailerons_app_android.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField() {

    Text(text = "DateField")

    val currentDate = Calendar.getInstance().timeInMillis
    var selectedDate by remember {
        mutableStateOf(currentDate)
    }

    Text(text = "Date choisie = ${selectedDate}")

    var showDialog by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = currentDate,
        initialDisplayMode = DisplayMode.Picker
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
                    .padding(top = 28.dp, start = 5.dp, end = 5.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.padding(2.dp),
                    title = {
                        Text(text = "Date de l'observation")
                    },
                    showModeToggle = false
                )
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Dismiss")
                    }
                    TextButton(
                        onClick = {
                            showDialog = false
                            selectedDate = datePickerState.selectedDateMillis!!
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }

    }
    Button(
        onClick = { showDialog = true }
    ) {
        Text(text = "Show Date Picker")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourField() {

    Text(text = "HourField")

    var selectedHour by remember {
        mutableStateOf(0)
    }

    var selectedMinute by remember {
        mutableStateOf(0)
    }

    Text(text = "Heure choisie = $selectedHour : $selectedMinute")

    var showDialog by remember {
        mutableStateOf(false)
    }

    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
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
                        Text(text = "Dismiss")
                    }
                    TextButton(
                        onClick = {
                            showDialog = false
                            selectedHour = timePickerState.hour
                            selectedMinute = timePickerState.minute
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }

    Button(
        onClick = { showDialog = true }
    ) {
        Text(text = "Show Time Picker")
    }
}


@Composable
fun LocationField() {
    Text(text = "LocationField")
}

@Composable
fun DepthField() {
    Text(text = "DepthField")
    TextField(
        value = "hey",
        onValueChange = { },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun SituationField() {
    Text(text = "SituationField")
    val situationList = LocalContext.current.resources.getStringArray(R.array.situation_field_array)
    var selected by remember { mutableStateOf(situationList.first()) }
    var expanded by remember { mutableStateOf(false) }
    Box {
        Column {
            OutlinedTextField(
                value = (selected),
                onValueChange = { },
                label = { Text(text = "Situation") },
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
                            selected = entry
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

//@Composable
//fun Map(context: Context) {
//    var mapView = MapView(context)
//    mapView.mapboxMap.setCamera(
//        CameraOptions.Builder()
//            .center(Point.fromLngLat(-98.0, 39.5))
//            .pitch(0.0)
//            .zoom(2.0)
//            .bearing(0.0)
//            .build()
//    )
//    setContentView(mapView)
//    with(mapView) {
//        location.locationPuck = createDefault2DPuck(withBearing = true)
//        location.enabled = true
//        location.puckBearing = com.mapbox.maps.plugin.PuckBearing.COURSE
//        viewport.transitionTo(
//            targetState = viewport.makeFollowPuckViewportState(),
//            transition = viewport.makeImmediateViewportTransition()
//        )
//}