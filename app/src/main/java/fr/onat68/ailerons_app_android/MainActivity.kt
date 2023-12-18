package fr.onat68.ailerons_app_android

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fr.onat68.ailerons_app_android.Constants.REQUEST_CODE_LOCATION_PERMISSION
import fr.onat68.ailerons_app_android.screens.ObservationContext.ObservationContextScreen
import fr.onat68.ailerons_app_android.ui.theme.AileronsAppAndroidTheme
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestPermissions(this)
        super.onCreate(savedInstanceState)
        setContent {
            AileronsAppAndroidTheme {
                // A surface container using the 'background' color from the theme
                ObservationContextScreen()
            }
        }
    }
}

fun requestPermissions(activity: Activity) {
    if (LocationUtility.hasLocationPermissions(activity)) {
        return
    }
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        EasyPermissions.requestPermissions(
            activity,
            "You need to accept location permissions",
            REQUEST_CODE_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    } else {
        EasyPermissions.requestPermissions(
            activity,
            "You need to accept location permissions",
            REQUEST_CODE_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )
    }
}
