package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional

data class ProfessionalListUiState(
    val professionals: List<Professional> = listOf(),
    val isLoading: Boolean = false
)
