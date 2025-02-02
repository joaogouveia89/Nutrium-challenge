package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import kotlinx.coroutines.flow.Flow

interface ProfessionalsRepository {
    fun getProfessionals(
        filterType: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Professional>>
}