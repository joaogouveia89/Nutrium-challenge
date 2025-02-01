package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation

object ProfessionalDetailsNav {
    const val professionalIdIdentifier = "professional_id"
    private const val _route = Routes.PROFESSIONAL_DETAIL_ROUTE
    val route = "$_route?$professionalIdIdentifier={$professionalIdIdentifier}"

    fun passProfessionalId(professionalId: Long) =
        "$_route?$professionalIdIdentifier=$professionalId"
}