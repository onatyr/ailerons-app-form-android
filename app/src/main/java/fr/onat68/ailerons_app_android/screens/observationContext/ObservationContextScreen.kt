package fr.onat68.ailerons_app_android.screens.observationContext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ObservationContextScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DateField()
        Spacer(modifier = Modifier.height(16.dp))
        HourField()
        Spacer(modifier = Modifier.height(16.dp))
        LocationField()
        Spacer(modifier = Modifier.height(16.dp))
        DepthField()
        Spacer(modifier = Modifier.height(16.dp))
        SituationField()
    }
}
