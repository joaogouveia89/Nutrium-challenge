package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListScreen(
    uiState: ProfessionalListUiState,
    filterTypesEntries: List<String>,
    onProfessionalClick: (Professional) -> Unit,
    onFilterTypeSelected: (Int) -> Unit,
    onErrorRetryClick: () -> Unit
) {

    val professionals = uiState.professionals.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = professionals.loadState.refresh,
        appendState = professionals.loadState.append,
        currentFilterType = uiState.filterType,
        filterTypesEntries = filterTypesEntries,
        onProfessionalClick = onProfessionalClick,
        onFilterTypeSelected = onFilterTypeSelected,
        onErrorRetryClick = onErrorRetryClick
    )
}