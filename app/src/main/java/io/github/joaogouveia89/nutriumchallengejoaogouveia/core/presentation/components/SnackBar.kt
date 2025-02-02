package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SnackBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    contentColor: Color,
    onDismiss: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RectangleShape)
            .padding(12.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = text,

            color = contentColor
        )
        onDismiss?.let {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { it.invoke() },
                imageVector = Icons.Default.Close,
                tint = contentColor,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ErrorSnackBar(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: (() -> Unit)? = null
) {
    SnackBar(
        modifier = modifier,
        text = text,
        contentColor = Color.White,
        backgroundColor = MaterialTheme.colorScheme.error,
        onDismiss = onDismiss
    )
}

@Preview
@Composable
private fun SnackBarPreview() {
    SnackBar(
        text = "Blue SnackBar",
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        onDismiss = {},
    )
}

@Preview
@Composable
private fun ErrorSnackBarPreview() {
    ErrorSnackBar(
        text = "Error SnackBar",
        onDismiss = {},
    )
}

@Preview
@Composable
private fun ErrorSnackBarNoDismissPreview() {
    ErrorSnackBar(
        text = "Error SnackBar"
    )
}