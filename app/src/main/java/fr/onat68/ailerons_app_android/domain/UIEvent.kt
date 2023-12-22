package fr.onat68.ailerons_app_android.domain

open class UIEvent {
    data class SpeciesSelected(val species: String): UIEvent()
    object Submit: UIEvent()
}