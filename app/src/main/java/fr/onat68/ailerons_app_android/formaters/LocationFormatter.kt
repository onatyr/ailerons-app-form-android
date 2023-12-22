package fr.onat68.ailerons_app_android.formaters

import com.google.android.gms.maps.model.LatLng

class LocationFormatter(location: LatLng, length: Int) {
    val latitude = String.format("%.${length}f", location.latitude.toFloat())
    val longitude = String.format("%.${length}f", location.longitude.toFloat())
}