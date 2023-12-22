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
    lateinit var currentLocation: LatLng

    val requestLocation =
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { lastLocation: Location -> // Attention, peut faire planter dans de rare cas car nullable
                _newForm.value = _newForm.value.copy(location = LatLng(lastLocation.latitude,lastLocation.longitude))
                currentLocation = LatLng(lastLocation.latitude,lastLocation.longitude)
            }

   fun getLastLocation() {
    _newForm.value = _newForm.value.copy(location = currentLocation)
   }

    fun onDateChange(newDate: Long){
        _newForm.value = _newForm.value.copy(date = newDate)
    }
    fun onTimeChange(newHour: Int, newMin: Int) {
        _newForm.value = _newForm.value.copy(hour = newHour, min = newMin)
    }
    fun onLocationChange(newLocation: LatLng){
        _newForm.value = _newForm.value.copy(location = newLocation)
    }
    fun onDepthChange(newDepth: String) {
        if (newDepth.isDigitsOnly()) {
            _newForm.value = _newForm.value.copy(depth = newDepth)
        }
    }
    fun onSituationChange(newSituation: String){
        _newForm.value = _newForm.value.copy(situation = newSituation)
    }
}