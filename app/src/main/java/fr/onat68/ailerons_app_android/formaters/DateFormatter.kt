package fr.onat68.ailerons_app_android.formaters

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.util.Date

@Suppress("DEPRECATION")
class DateFormatter(date: Date) {
    private val day = if (date.day.toString().length < 2) "0${date.day.toString()}" else date.day.toString()
    private val month =
        if (date.month.toString().length < 2) "0${date.month.toString()}" else date.month.toString()
    private val year = (date.year + 1900).toString()
    val ddMMYYYY = "$day/$month/$year"

    @OptIn(ExperimentalMaterial3Api::class)
    val selectableDates = object : SelectableDates
    {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }

        override fun isSelectableYear(year: Int): Boolean {
            return year <= Date().year + 1900
        }
    }
}