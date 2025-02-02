package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfessionalsRepositoryImpl @Inject constructor(
    private val professionalsRemoteSource: ProfessionalsRemoteSource,
    private val dispatcher: CoroutineDispatcher
) : ProfessionalsRepository {

    private val cachedProfessionals = mutableMapOf<String, MutableMap<Int, List<Professional>>>()

    override fun getProfessionals(
        filterType: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Professional>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = {
            professionalsRemoteSource.getProfessionalsPagingSource(
                filterType = filterType,
                cache = cachedProfessionals
            )
        }
    ).flow.flowOn(dispatcher)
}