package fr.onat68.ailerons_app_android.screens.context

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.onat68.ailerons_app_android.FormulaireModel
import java.util.Date

@Composable
fun ContextScreen(contextViewModel: ContextViewModel, navController: NavController) {
    val newForm: State<FormulaireModel> =
        contextViewModel.newForm.collectAsState(initial = FormulaireModel())

    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DateField(contextViewModel::onDateChange, newForm.value.date)
            Text(Date().year.toString())
            Spacer(modifier = Modifier.height(16.dp))
            HourField(contextViewModel::onTimeChange, newForm.value.hour, newForm.value.min)
            Spacer(modifier = Modifier.height(16.dp))
            LocationField(newForm.value.location, navController::navigate)
            Spacer(modifier = Modifier.height(16.dp))
            DepthField(contextViewModel::onDepthChange, newForm.value.depth)
            Spacer(modifier = Modifier.height(16.dp))
            SituationField(contextViewModel::onSituationChange, newForm.value.situation)

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TimePickerPreview() {
    val timePickerState = rememberTimePickerState(
        initialHour = 1,
        initialMinute = 1
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
    ) {

        TimePicker(
            state = timePickerState,
            colors = TimePickerColors(
                clockDialColor = MaterialTheme.colorScheme.primary,
                selectorColor = Color.White,
                containerColor = Color.White,
                periodSelectorBorderColor = Color.White,
                clockDialSelectedContentColor = Color.Black,
                clockDialUnselectedContentColor = Color.Black,
                periodSelectorSelectedContainerColor = Color.Black,
                periodSelectorUnselectedContainerColor = Color.Black,
                periodSelectorSelectedContentColor = Color.Black,
                periodSelectorUnselectedContentColor = Color.Black,
                timeSelectorSelectedContainerColor = Color.Black,
                timeSelectorUnselectedContainerColor = Color.Black,
                timeSelectorSelectedContentColor = Color.Black,
                timeSelectorUnselectedContentColor = Color.Black
            )
        )
    }
}