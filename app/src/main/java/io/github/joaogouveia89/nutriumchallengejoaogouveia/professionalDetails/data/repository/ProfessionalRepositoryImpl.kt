package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.GetDetailsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.ProfessionalRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalRemoteSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfessionalRepositoryImpl @Inject constructor(
    private val remoteSource: ProfessionalRemoteSource
) : ProfessionalRepository {
    override suspend fun getDetails(id: Long) = flow {
        emit(GetDetailsState.Loading)
    }
}