package fr.onat68.ailerons_app_android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import fr.onat68.ailerons_app_android.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val fontDisplay = FontFamily(
    Font(googleFont = GoogleFont("Comfortaa"), fontProvider = provider)
)

private val fontBody = FontFamily(
    Font(googleFont = GoogleFont("Atkinson Hyperlegible"), fontProvider = provider)
)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 57.sp,
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 45.sp,
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 36.sp,
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 32.sp,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 17.sp,
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = fontDisplay,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.W700,
        fontFamily = fontDisplay,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontFamily = fontDisplay,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontBody,
        fontSize = 15.sp,
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontBody,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontBody,
        fontSize = 12.sp,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontFamily = fontDisplay,
        fontSize = 10.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = fontDisplay,
        fontWeight = FontWeight.W500,
        fontSize = 8.sp,
    ),
)