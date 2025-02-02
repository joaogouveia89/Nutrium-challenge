package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional

interface ProfessionalsRemoteSource {
    suspend fun getProfessionals(filterType: String): List<Professional>
}