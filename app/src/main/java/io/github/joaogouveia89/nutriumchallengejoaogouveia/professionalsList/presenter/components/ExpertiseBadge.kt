package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpertiseBadge(
    modifier: Modifier,
    text: String
) {
    Column(
        modifier = modifier
            .background(Color(0xFFC6C6C6))
            .padding(6.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
