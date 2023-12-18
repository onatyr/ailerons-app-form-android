package fr.onat68.ailerons_app_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.onat68.ailerons_app_android.screens.observationContext.ObservationContextScreen
import fr.onat68.ailerons_app_android.ui.theme.AileronsAppAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AileronsAppAndroidTheme {
                // A surface container using the 'background' color from the theme
                ObservationContextScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AileronsAppAndroidTheme {
        Greeting("Android")
    }
}