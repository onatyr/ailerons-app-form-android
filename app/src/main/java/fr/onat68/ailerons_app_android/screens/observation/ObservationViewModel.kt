package fr.onat68.ailerons_app_android.screens.observation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.onat68.ailerons_app_android.FormulaireModel
import fr.onat68.ailerons_app_android.domain.UIEvent
import fr.onat68.ailerons_app_android.domain.UIState
import fr.onat68.ailerons_app_android.domain.ValidationEvent
import fr.onat68.ailerons_app_android.domain.Validator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ObservationViewModel : ViewModel() {
    private val _newForm = MutableStateFlow(FormulaireModel())
    val newForm: Flow<FormulaireModel> = _newForm
    private var _uiState = mutableStateOf(UIState())
    val uiState: State<UIState> = _uiState

    val validationEvent = MutableSharedFlow<ValidationEvent>()
    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.SpeciesSelected -> {
                _uiState.value = _uiState.value.copy(
                    species = event.species
                )
            }
        }
    }
    private fun validateInputs() {
        val speciesResult = Validator.validateSpecies(_uiState.value.species)

        val hasError = listOf(
            speciesResult,
        ).any { !it.status }
        viewModelScope.launch {
            if (!hasError) {
                validationEvent.emit(ValidationEvent.Success)
            }
        }
    }
}