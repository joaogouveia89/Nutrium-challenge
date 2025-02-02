package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import kotlinx.coroutines.flow.Flow

interface ProfessionalsRepository {
    fun getProfessionals(filterType: String): Flow<GetProfessionalsState>
}