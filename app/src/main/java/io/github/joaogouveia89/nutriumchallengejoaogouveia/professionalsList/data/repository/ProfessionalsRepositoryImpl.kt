package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.repository

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.GetProfessionalsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.FilterType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfessionalsRepositoryImpl @Inject constructor(
    private val professionalsRemoteSource: ProfessionalsRemoteSource,
    private val dispatcher: CoroutineDispatcher
) : ProfessionalsRepository {

    private val cachedProfessionals = mutableMapOf<String, List<Professional>>()

    override fun getProfessionals(filterType: String): Flow<GetProfessionalsState> = flow {
        emit(GetProfessionalsState.Loading)

        val professionals = getFromCache(filterType) ?: professionalsRemoteSource
            .getProfessionals(filterType = filterType)
            .also {
                updateCache(filterType, it)
            }


        emit(GetProfessionalsState.Success(professionals))
    }.flowOn(dispatcher)

    private fun getFromCache(filterType: String): List<Professional>? =
        cachedProfessionals[filterType]

    private fun updateCache(filterType: String, professionals: List<Professional>) {
        cachedProfessionals[filterType] = professionals
    }
}