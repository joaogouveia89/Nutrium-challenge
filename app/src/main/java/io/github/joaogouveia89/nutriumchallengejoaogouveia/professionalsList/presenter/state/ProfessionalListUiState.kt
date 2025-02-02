package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.FilterType

data class ProfessionalListUiState(
    val professionals: List<Professional>? = null,
    val filterType: FilterType = FilterType.BEST_MATCH,
    val isLoading: Boolean = false
)
