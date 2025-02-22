package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.ProfessionalImage
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.ProfessionalRatingBar

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfessionalListItem(
    professional: Professional,
    onProfessionalClick: (Professional) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onProfessionalClick(professional)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
            Row {
                Column(Modifier.padding(bottom = 12.dp)) {
                    ProfessionalImage(
                        url = professional.profilePictureUrl,
                        fallback = professional.nameInitials
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        professional.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    ProfessionalRatingBar(
                        modifier = Modifier.padding(top = 8.dp),
                        rating = professional.rating,
                        ratingCount = professional.ratingCount
                    )
                    Languages(
                        modifier = Modifier.padding(top = 8.dp),
                        languages = professional.languages.joinToString(", ")
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 12.dp)) {
                professional.expertise.forEach {
                    ExpertiseBadge(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = it
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListItemPreview() {
    ProfessionalListItem(
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
        onProfessionalClick = {}
    )
}