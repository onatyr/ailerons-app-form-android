package fr.onat68.ailerons_app_android

import android.annotation.SuppressLint
import com.google.android.gms.maps.model.LatLng
import java.time.LocalTime
import java.util.Calendar
import java.util.UUID

@SuppressLint("NewApi")
data class FormulaireModel(
    val id: String = UUID.randomUUID().toString(),
    val date: Long = Calendar.getInstance().timeInMillis,
    val hour: Int = LocalTime.now().hour,
    val min: Int = LocalTime.now().minute,
    val location: LatLng = LatLng(48.8738556,2.3588788),
    val depth: String = "",
    val situation: String = "Plongée"
)