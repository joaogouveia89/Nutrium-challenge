package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional

interface ProfessionalDetailsRemoteSource {
    suspend fun getProfessionalDetails(id: Long): Professional
}