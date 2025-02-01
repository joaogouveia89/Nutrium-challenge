package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter

import androidx.compose.runtime.Composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state.ProfessionalDetailsUiState

@Composable
fun ProfessionalDetailsScreen(
    uiState: ProfessionalDetailsUiState,
    isAboutMeExpanded: Boolean,
    onAboutMeExpandCollapseClick: () -> Unit
) {
//    val profissional = Professional(
//        id = 1,
//        name = "John",
//        profilePictureUrl = "",
//        rating = 3,
//        ratingCount = 21,
//        aboutMe = "",
//        expertise = listOf("exp1", "exp2", "exp3"),
//        languages = listOf("lan1", "lan2", "lan3")
//    )

    val professional = uiState.professional
    ProfessionalDetailsContent(
        professional,
        isAboutMeExpanded = isAboutMeExpanded,
        onAboutMeExpandCollapseClick = onAboutMeExpandCollapseClick
    )
}