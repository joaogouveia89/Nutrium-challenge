package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.runtime.Composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListScreen(uiState: ProfessionalListUiState) {
    ProfessionalListContent(
        uiState = uiState
    )
}