package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional


sealed class GetProfessionalsState {
    data object Loading : GetProfessionalsState()
    data class Success(val professionals: List<Professional>) : GetProfessionalsState()
    data object Error : GetProfessionalsState()
}