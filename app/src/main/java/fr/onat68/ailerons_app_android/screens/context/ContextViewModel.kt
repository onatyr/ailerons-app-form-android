package fr.onat68.ailerons_app_android.screens.context

import android.annotation.SuppressLint
import android.location.Location
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import fr.onat68.ailerons_app_android.FormulaireModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("NewApi", "MissingPermission")
class ContextViewModel(fusedLocationClient: FusedLocationProviderClient) : ViewModel() {

    private val _newForm = MutableStateFlow(FormulaireModel())
    val newForm: Flow<FormulaireModel> = _newForm

    private var _location = MutableStateFlow(LatLng(48.8738556,2.3588788)) //(Location("observationLocation"))
    val location: Flow<LatLng> = _location
    val requestLocation =
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { lastLocation: Location -> // Attention, peut faire planter dans de rare cas car nullable
                _location.value = LatLng(lastLocation.latitude,lastLocation.longitude)
            }

    fun onTimeChange(hour: Int, min: Int) {
        _newForm.value = _newForm.value.copy(hour = hour, min = min)
    }

    fun onDepthChange(depth: String) {
        if (depth.isDigitsOnly()) {
            _newForm.value = _newForm.value.copy(depth = depth)
        }
    }

}