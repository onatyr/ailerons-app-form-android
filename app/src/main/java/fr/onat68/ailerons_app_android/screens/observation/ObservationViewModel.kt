package fr.onat68.ailerons_app_android.screens.observation

import androidx.lifecycle.ViewModel
import fr.onat68.ailerons_app_android.FormulaireModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ObservationViewModel : ViewModel() {
    private val _newForm = MutableStateFlow(FormulaireModel())
    val newForm: Flow<FormulaireModel> = _newForm

    fun onSpeciesChange(species: String) {
        _newForm.value = _newForm.value.copy(species = species)
    }
}