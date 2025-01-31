package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository

import kotlinx.coroutines.flow.Flow

interface ProfessionalsRepository {
    suspend fun getProfessionals(): Flow<GetProfessionalsState>
}