package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.MultipleChoiceSelect
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components.ProfessionalListItem
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState

@Composable
fun ProfessionalListContent(
    uiState: ProfessionalListUiState,
    onProfessionalClick: (Professional) -> Unit
) {
    var isDialogShow by remember { mutableStateOf(false) }
    var optionFilterSelected by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        MultipleChoiceSelect(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(0.9f)
                .border(width = 1.dp, color = Black, shape = RectangleShape)
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { isDialogShow = true },
            options = listOf("Best Match", "Most Popular", "Rating"),
            isDialogShow = isDialogShow,
            selectedIndex = optionFilterSelected,
            onChose = {
                optionFilterSelected = it
                isDialogShow = false
            }
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxSize(),
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