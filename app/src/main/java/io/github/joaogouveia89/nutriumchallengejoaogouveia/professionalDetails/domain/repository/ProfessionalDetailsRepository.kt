package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository

import kotlinx.coroutines.flow.Flow

interface ProfessionalDetailsRepository {
    suspend fun getDetails(id: Long): Flow<GetDetailsState>
}