package fr.onat68.ailerons_app_android.screens.observation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.onat68.ailerons_app_android.FormulaireModel
import fr.onat68.ailerons_app_android.domain.UIEvent
import fr.onat68.ailerons_app_android.domain.ValidationEvent
import fr.onat68.ailerons_app_android.screens.context.DateField
import fr.onat68.ailerons_app_android.screens.context.DepthField
import fr.onat68.ailerons_app_android.screens.context.HourField
import fr.onat68.ailerons_app_android.screens.context.LocationField
import fr.onat68.ailerons_app_android.screens.context.SituationField

@Composable
fun ObservationScreen(observationViewModel: ObservationViewModel, navController: NavController) {
    val state = observationViewModel.uiState.value
    val context = LocalContext.current
    val localFocus = LocalFocusManager.current
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SpeciesField(
                label = "Espèces",
                onSpeciesChange = {
                observationViewModel.onEvent(UIEvent.SpeciesSelected(it))
                Log.d("speciesUIEvent", it)
            })
        }
    }
}
