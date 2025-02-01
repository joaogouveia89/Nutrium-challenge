package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state.ProfessionalDetailsUiState

@Composable
fun ProfessionalDetailsScreen(
    uiState: ProfessionalDetailsUiState,
    isAboutMeExpanded: Boolean,
    onAboutMeExpandCollapseClick: () -> Unit
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ProfessionalDetailsContent(
            professional = uiState.professional,
            isAboutMeExpanded = isAboutMeExpanded,
            onAboutMeExpandCollapseClick = onAboutMeExpandCollapseClick
        )
    }
}