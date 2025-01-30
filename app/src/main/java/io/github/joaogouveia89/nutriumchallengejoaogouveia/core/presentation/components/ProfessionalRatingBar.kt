package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfessionalRatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    ratingCount: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until 5) {
            Icon(
                imageVector = if (i < rating)
                    Icons.Default.StarRate
                else
                    Icons.Outlined.StarRate,
                tint = Color.Blue,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "$rating/5 ($ratingCount)"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPreview() {
    ProfessionalRatingBar(
        rating = 3,
        ratingCount = 9
    )
}