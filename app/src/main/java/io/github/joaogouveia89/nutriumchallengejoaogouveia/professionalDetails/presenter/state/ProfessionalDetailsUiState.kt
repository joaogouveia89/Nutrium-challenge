package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional

data class ProfessionalDetailsUiState(
    val professional: Professional = Professional(),
    val isLoading: Boolean = false
)
