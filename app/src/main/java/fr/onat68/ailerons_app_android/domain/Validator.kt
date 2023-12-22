package fr.onat68.ailerons_app_android.domain

object Validator {
    fun validateSpecies(species: String): ValidationResult {
        return ValidationResult(species.isNotEmpty() && (species == "requin" || species == "raie" || species == "chimère" || species == "inconnue"))
    }
}


data class ValidationResult(
    val status: Boolean = false,
)