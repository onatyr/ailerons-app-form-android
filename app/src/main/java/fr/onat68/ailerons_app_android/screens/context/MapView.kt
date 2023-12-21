package fr.onat68.ailerons_app_android.screens.context

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import fr.onat68.ailerons_app_android.FormulaireModel
import kotlinx.coroutines.launch

@Composable
fun MapView(contextViewModel: ContextViewModel) {
    val newForm: State<FormulaireModel> =
        contextViewModel.newForm.collectAsState(initial = FormulaireModel())
    val cameraPositionState =  rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newForm.value.location, 10f)
    }
    val coroutineScope = rememberCoroutineScope()

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { it ->
            contextViewModel.onLocationChange(it)
        },
    ) {
        Marker(
            state = MarkerState(position = newForm.value.location)
        )
    }
    DisposableEffect(newForm.value.location) {
        onDispose {
            coroutineScope.launch {
                cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(newForm.value.location, cameraPositionState.position.zoom)))
            }
        }
    }
}
