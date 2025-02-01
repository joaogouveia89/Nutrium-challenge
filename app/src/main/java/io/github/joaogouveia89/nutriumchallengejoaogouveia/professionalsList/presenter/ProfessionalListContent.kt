package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components.ProfessionalListItem
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListContent(
    uiState: ProfessionalListUiState,
    onProfessionalClick: (Professional) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(uiState.professionals) {
            ProfessionalListItem(
                it,
                onProfessionalClick = onProfessionalClick
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentPreview() {
    ProfessionalListContent(
        uiState = ProfessionalListUiState(
            professionals = listOf(
                Professional(
                    aboutMe = "Emma Williams specializes in Sports Nutrition and Weight Gain, with a passion for promoting health and well-being.",
                    expertise = listOf(
                        "Sports Nutrition",
                        "Weight Gain"
                    ),
                    id = 1,
                    languages = listOf(
                        "German",
                        "Portuguese",
                        "English"
                    ),
                    name = "Emma Williams",
                    profilePictureUrl = "https://thispersondoesnotexist.com/image-1.jpg",
                    rating = 3,
                    ratingCount = 80
                ),
                Professional(
                    aboutMe = "Emma Williams specializes in Sports Nutrition and Weight Gain, with a passion for promoting health and well-being.",
                    expertise = listOf(
                        "Sports Nutrition",
                        "Weight Gain"
                    ),
                    id = 1,
                    languages = listOf(
                        "German",
                        "Portuguese",
                        "English"
                    ),
                    name = "Emma Williams",
                    profilePictureUrl = "https://thispersondoesnotexist.com/image-1.jpg",
                    rating = 4,
                    ratingCount = 80
                )
            )
        ),
        onProfessionalClick = {}
    )
}