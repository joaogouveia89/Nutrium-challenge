package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.runtime.Composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListScreen(
    uiState: ProfessionalListUiState,
    filterTypesEntries: List<String>,
    onProfessionalClick: (Professional) -> Unit,
    onFilterTypeSelected: (Int) -> Unit
) {
    ProfessionalListContent(
        uiState = uiState,
        filterTypesEntries = filterTypesEntries,
        onProfessionalClick = onProfessionalClick,
        onFilterTypeSelected = onFilterTypeSelected
    )
}