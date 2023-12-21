package fr.onat68.ailerons_app_android.screens.observation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.onat68.ailerons_app_android.FormulaireModel
import fr.onat68.ailerons_app_android.screens.context.DateField
import fr.onat68.ailerons_app_android.screens.context.DepthField
import fr.onat68.ailerons_app_android.screens.context.HourField
import fr.onat68.ailerons_app_android.screens.context.LocationField
import fr.onat68.ailerons_app_android.screens.context.SituationField

@Composable
fun ObservationScreen(observationViewModel: ObservationViewModel, navController: NavController) {
    val newForm: State<FormulaireModel> =
        observationViewModel.newForm.collectAsState(initial = FormulaireModel())
    val species = listOf("requin","raie","chimère","inconnue")
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Picker(species, observationViewModel::onSpeciesChange)
        }
    }
}

