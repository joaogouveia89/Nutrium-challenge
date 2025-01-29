package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Languages(
    modifier: Modifier = Modifier,
    languages: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = languages
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LanguagesPreview() {
    Languages(languages = "Portuguese, English")
}