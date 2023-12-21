package fr.onat68.ailerons_app_android

import android.annotation.SuppressLint
import android.location.Location
import java.time.LocalTime
import java.util.Calendar
import java.util.UUID

@SuppressLint("NewApi")
data class FormulaireModel(
    val id: String = UUID.randomUUID().toString(),
    val date: Long = Calendar.getInstance().timeInMillis,
    val hour: Int = LocalTime.now().hour,
    val min: Int = LocalTime.now().minute,
    val location: Location? = null,
    val depth: String = "",
    val situation: String = "Plongée",
    val species: String = "",
    val speciesName: String = "",
    val sex: String = "",
    val numberObserved: Int = 0,
    val exactNumber: Boolean = false,
    val specimenSize: Int = 0,
    val exactSize: Boolean = false,
)