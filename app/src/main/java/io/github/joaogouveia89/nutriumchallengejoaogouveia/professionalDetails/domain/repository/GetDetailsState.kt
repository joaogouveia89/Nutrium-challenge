package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional

sealed class GetDetailsState {
    data object Loading : GetDetailsState()
    data object SourceError : GetDetailsState()
    data class Success(val professional: Professional) : GetDetailsState()
}