package fr.onat68.ailerons_app_android.domain

sealed class ValidationEvent {
    object Success: ValidationEvent()
}