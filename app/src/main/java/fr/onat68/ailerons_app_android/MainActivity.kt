package fr.onat68.ailerons_app_android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import fr.onat68.ailerons_app_android.screens.context.ContextViewModel
import fr.onat68.ailerons_app_android.screens.context.ObservationContextScreen
import fr.onat68.ailerons_app_android.ui.theme.AileronsAppAndroidTheme

class MainActivity : ComponentActivity() {

    lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            return
        }
        fusedLocationClient

        super.onCreate(savedInstanceState)
        val contextViewModel = ContextViewModel(fusedLocationClient)
        setContent {
            AileronsAppAndroidTheme {
                ObservationContextScreen(contextViewModel)
            }
        }
    }
}
