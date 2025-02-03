package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import io.github.joaogouveia89.nutriumchallengejoaogouveia.ui.theme.NutriumGreen


@Composable
fun ProfessionalImage(
    url: String,
    fallback: String
) {
    val sizeResolver = rememberConstraintsSizeResolver()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(url)
            .size(sizeResolver)
            .build(),
    )

    val painterState by painter.state.collectAsState()

    if (painterState is AsyncImagePainter.State.Error) {
        Column(
            modifier = Modifier
                .size(80.dp)
                .background(NutriumGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = fallback,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
        }
    } else {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .then(sizeResolver),
        )
    }
}