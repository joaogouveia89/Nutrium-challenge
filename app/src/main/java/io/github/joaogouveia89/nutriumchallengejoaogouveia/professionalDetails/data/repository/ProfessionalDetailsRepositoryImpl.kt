package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.GetDetailsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.ProfessionalDetailsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalDetailsRemoteSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfessionalDetailsRepositoryImpl @Inject constructor(
    private val remoteSource: ProfessionalDetailsRemoteSource
) : ProfessionalDetailsRepository {
    override suspend fun getDetails(id: Long) = flow {
        emit(GetDetailsState.Loading)
    }
}