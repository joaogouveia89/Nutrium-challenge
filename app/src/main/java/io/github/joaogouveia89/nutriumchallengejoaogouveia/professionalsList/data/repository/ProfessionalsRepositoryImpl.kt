package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.GetProfessionalsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfessionalsRepositoryImpl @Inject constructor(
    private val professionalsRemoteSource: ProfessionalsRemoteSource,
    private val dispatcher: CoroutineDispatcher
) : ProfessionalsRepository {
    override suspend fun getProfessionals(): Flow<GetProfessionalsState> = flow {
        emit(GetProfessionalsState.Loading)
        emit(GetProfessionalsState.Success(professionalsRemoteSource.getProfessionals()))
    }.flowOn(dispatcher)
}