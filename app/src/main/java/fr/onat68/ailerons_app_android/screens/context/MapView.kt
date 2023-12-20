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
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun MapView(contextViewModel: ContextViewModel) {
    val location: State<LatLng> =
        contextViewModel.location.collectAsState(initial = LatLng(48.8738556, 2.3588788))
    val cameraPositionState =  rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location.value, 10f)
    }
    val coroutineScope = rememberCoroutineScope()

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { it ->
            contextViewModel.onChangeLocation(it)
        },
    ) {
        Marker(
            state = MarkerState(position = location.value)
        )
    }
    DisposableEffect(location.value) {
        onDispose {
            coroutineScope.launch {
                cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(location.value, cameraPositionState.position.zoom)))
            }
        }
    }
}
