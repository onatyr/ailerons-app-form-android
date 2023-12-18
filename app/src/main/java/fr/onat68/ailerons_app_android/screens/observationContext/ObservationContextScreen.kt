package fr.onat68.ailerons_app_android.screens.observationContext

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
@Composable
fun ObservationContextScreen(){
    Column {
        DateField()
        HourField()
        LocationField()
        DepthField()
        SituationField()
    }
}