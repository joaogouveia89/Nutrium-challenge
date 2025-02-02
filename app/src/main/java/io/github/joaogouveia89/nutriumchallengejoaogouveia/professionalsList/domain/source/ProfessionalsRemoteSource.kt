package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.source.ProfessionalsPagingSource

interface ProfessionalsRemoteSource {
    suspend fun getProfessionals(
        filterType: String,
        limit: Int,
        offset: Int,
    ): List<Professional>
    fun getProfessionalsPagingSource(filterType: String): ProfessionalsPagingSource =
        ProfessionalsPagingSource(
            remoteDataSource = this,
            filterType = filterType
        )
}