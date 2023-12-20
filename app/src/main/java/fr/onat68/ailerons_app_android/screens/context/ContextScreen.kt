package fr.onat68.ailerons_app_android.screens.context

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import fr.onat68.ailerons_app_android.FormulaireModel

@Composable
fun ContextScreen(contextViewModel: ContextViewModel, navController: NavController) {
    val newForm: State<FormulaireModel> =
        contextViewModel.newForm.collectAsState(initial = FormulaireModel())
    val location: State<LatLng> =
        contextViewModel.location.collectAsState(initial = LatLng(48.8738556, 2.3588788))

    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DateField()
            Spacer(modifier = Modifier.height(16.dp))
            HourField(contextViewModel::onTimeChange, newForm.value.hour, newForm.value.min)
            Spacer(modifier = Modifier.height(16.dp))
            LocationField(location.value, navController::navigate)
            Spacer(modifier = Modifier.height(16.dp))
            DepthField(contextViewModel::onDepthChange, newForm.value.depth)
            Spacer(modifier = Modifier.height(16.dp))
            SituationField()

        }
    }
}
