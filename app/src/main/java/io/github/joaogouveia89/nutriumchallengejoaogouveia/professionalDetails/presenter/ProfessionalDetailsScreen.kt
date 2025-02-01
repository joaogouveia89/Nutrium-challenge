package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter

import androidx.compose.runtime.Composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state.ProfessionalDetailsUiState

@Composable
fun ProfessionalDetailsScreen(
    uiState: ProfessionalDetailsUiState,
    isAboutMeExpanded: Boolean,
    onAboutMeExpandCollapseClick: () -> Unit
) {
    val professional = uiState.professional

    ProfessionalDetailsContent(
        professional,
        isAboutMeExpanded = isAboutMeExpanded,
        onAboutMeExpandCollapseClick = onAboutMeExpandCollapseClick
    )
}