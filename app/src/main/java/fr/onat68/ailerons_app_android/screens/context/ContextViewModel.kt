package fr.onat68.ailerons_app_android.screens.context

import android.annotation.SuppressLint
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import fr.onat68.ailerons_app_android.FormulaireModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("NewApi")
class ContextViewModel: ViewModel() {

    private val _newForm = MutableStateFlow(FormulaireModel())

    val newForm: Flow<FormulaireModel> = _newForm

    fun onTimeChange(hour: Int, min: Int){
        _newForm.value = _newForm.value.copy(hour = hour, min = min)
    }
    fun onDepthChange(depth: String){
        if(depth.isDigitsOnly()){
            _newForm.value = _newForm.value.copy(depth = depth)
        }
    }

}