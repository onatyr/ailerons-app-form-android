package fr.onat68.ailerons_app_android.formaters

import java.util.Date

@Suppress("DEPRECATION")
class DateFormatter(date: Date) {
    val day = if(date.day.toString().length < 2) "0${date.day.toString()}" else date.day.toString()
    val month = if(date.month.toString().length < 2) "0${date.month.toString()}" else date.month.toString()
    val year = (date.year+1900).toString()
    val ddMMYYYY = "$day/$month/$year"
}