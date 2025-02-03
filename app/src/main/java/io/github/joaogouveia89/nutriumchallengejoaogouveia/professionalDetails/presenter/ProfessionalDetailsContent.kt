package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.joaogouveia89.nutriumchallengejoaogouveia.R
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.ProfessionalRatingBar
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.ktx.bottomBorder
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.previews.singleProfessional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.ui.theme.lightGray

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ProfessionalDetailsContent(
    professional: Professional,
    isAboutMeExpanded: Boolean,
    onAboutMeExpandCollapseClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .bottomBorder(2.dp, Color.Black),
            color = lightGray,
            shadowElevation = 12.dp
        ) {
            Column {
                Icon(
                    modifier = Modifier
                        .clickable { onBackClick() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(80.dp),
                        model = professional.profilePictureUrl,
                        contentDescription = null
                    )
                    Column {
                        Text(
                            text = professional.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        ProfessionalRatingBar(
                            modifier = Modifier.padding(top = 8.dp),
                            rating = professional.rating,
                            ratingCount = professional.ratingCount
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(R.string.professional_details_label_about_me),
                style = MaterialTheme.typography.titleMedium
            )

            var originalTextLayoutNumberOfLines = 0
            var truncatedTextLayoutNumberOfLines = 0

            BoxWithConstraints {
                val textMeasurer = rememberTextMeasurer()

                val style = MaterialTheme.typography.bodySmall

                val fullTextLayoutResult = remember(professional.aboutMe, style, constraints) {
                    textMeasurer.measure(
                        text = professional.aboutMe,
                        style = style,
                        constraints = constraints
                    )
                }

                val truncatedTextLayoutResult = remember(professional.aboutMe, style, constraints) {
                    textMeasurer.measure(
                        text = professional.aboutMe,
                        style = style,
                        maxLines = 3,
                        constraints = constraints
                    )
                }

                originalTextLayoutNumberOfLines = fullTextLayoutResult.lineCount
                truncatedTextLayoutNumberOfLines = truncatedTextLayoutResult.lineCount

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = professional.aboutMe,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isAboutMeExpanded) Int.MAX_VALUE else 3
                )
            }

            ExpandCollapseButton(
                modifier = Modifier,
                text = stringResource(
                    if (isAboutMeExpanded)
                        R.string.professional_details_collapse
                    else
                        R.string.professional_details_show_more
                ),
                icon = if (isAboutMeExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                onClick = {
                    if (originalTextLayoutNumberOfLines > truncatedTextLayoutNumberOfLines) {
                        onAboutMeExpandCollapseClick()
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.professional_details_nothing_to_expand_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    }
}


@Composable
fun ExpandCollapseButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalDetailsContentPreview() {
    var isAboutMeExpanded by rememberSaveable { mutableStateOf(false) }

    ProfessionalDetailsContent(
        professional = singleProfessional,
        isAboutMeExpanded = isAboutMeExpanded,
        onAboutMeExpandCollapseClick = { isAboutMeExpanded = !isAboutMeExpanded },
        onBackClick = {}
    )
}
