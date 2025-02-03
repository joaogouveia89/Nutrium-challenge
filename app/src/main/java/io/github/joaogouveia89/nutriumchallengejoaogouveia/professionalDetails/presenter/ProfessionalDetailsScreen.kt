package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.R
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.GenericErrorScreen
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state.ProfessionalDetailsUiState

@Composable
fun ProfessionalDetailsScreen(
    uiState: ProfessionalDetailsUiState,
    isAboutMeExpanded: Boolean,
    onAboutMeExpandCollapseClick: () -> Unit,
    onBackClick: () -> Unit
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (uiState.isError) {
        GenericErrorScreen(
            errorMessage = stringResource(R.string.professional_details_loading_error),
            onRetryClick = {}
        )
    } else {
        ProfessionalDetailsContent(
            professional = uiState.professional,
            isAboutMeExpanded = isAboutMeExpanded,
            onAboutMeExpandCollapseClick = onAboutMeExpandCollapseClick,
            onBackClick = onBackClick
        )
    }
}