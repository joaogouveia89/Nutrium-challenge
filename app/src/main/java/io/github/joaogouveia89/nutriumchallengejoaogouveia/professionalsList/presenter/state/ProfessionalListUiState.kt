package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state

import androidx.paging.PagingData
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.FilterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfessionalListUiState(
    val professionals: Flow<PagingData<Professional>> = emptyFlow(),
    val filterType: FilterType = FilterType.BEST_MATCH
)
