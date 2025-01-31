package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListScreen(uiState: ProfessionalListUiState) {
    if(uiState.isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else{
        ProfessionalListContent(
            uiState = uiState
        )
    }
}