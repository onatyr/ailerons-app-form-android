package fr.onat68.ailerons_app_android.screens.observation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.onat68.ailerons_app_android.ui.theme.backgroundDark

// la gestion des styles c'est nimp mais a priori le reste marche bien
@Composable
fun Picker(options: List<String>, onSelectionChange: (String) -> Unit ) {
    var selectedTabIndex by remember {
        mutableStateOf(options.lastIndex)
    }

    TabRow(selectedTabIndex = selectedTabIndex, indicator = { tabPositions ->
        TabRowDefaults.Indicator(
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .tabIndicatorOffset(tabPositions[selectedTabIndex])
                .fillMaxWidth(),
        )
    }, modifier = Modifier.background(Color.White)) { options.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index
                          onSelectionChange(tab)
                          Log.d("tabClick", tab)},
                modifier = Modifier
                    .background(
                        if(selectedTabIndex == index) MaterialTheme.colorScheme.secondary else Color.White)
                    .padding(4.dp)
                    .height(24.dp)
                ,
                text = {Text(tab, style = MaterialTheme.typography.labelLarge, color = if(selectedTabIndex == index) Color.White else Color.DarkGray)}
            )
        }
    }
}

@Composable
fun SpeciesField(label: String, onSpeciesChange: (String) -> Unit) {
    val species = listOf("requin", "raie","chimère","inconnue")
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Espèce observée", style = MaterialTheme.typography.headlineSmall)
        Picker(options = species, onSelectionChange = onSpeciesChange)
    }

}



