package fr.onat68.ailerons_app_android.screens.context.UIElements

import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class DatePickerUI {
    companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun CustomDatePickerColors(): DatePickerColors {
            return DatePickerDefaults.colors(
                containerColor = Color.Black,
                titleContentColor = Color.Black,
                headlineContentColor = Color.Black,
                weekdayContentColor = Color.Black,
                subheadContentColor = Color.Black,
                yearContentColor = Color.Black,
                currentYearContentColor = Color.Black,
                selectedYearContentColor = Color.Black,
                selectedYearContainerColor = Color.Black,
                dayContentColor = Color.Black,
                disabledDayContentColor = Color.Black,
                selectedDayContentColor = Color.Black,
                disabledSelectedDayContentColor = Color.Black,
                selectedDayContainerColor = Color.Black,
                disabledSelectedDayContainerColor = Color.Black,
                todayContentColor = Color.Black,
                todayDateBorderColor = Color.Black,
            )
        }
    }
}