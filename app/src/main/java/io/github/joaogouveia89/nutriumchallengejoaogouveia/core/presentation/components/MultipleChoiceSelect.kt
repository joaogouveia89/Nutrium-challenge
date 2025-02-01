package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

//TODO: Make a version of this dialog for cases on which has more than 4 options,
// in this case it will be necessary a lazy list with scrolling somehow
@Composable
fun MultipleChoiceSelect(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedIndex: Int = 0,
    isDialogShow: Boolean,
    onChose: (index: Int) -> Unit
) {
    if (isDialogShow) {
        Dialog(onDismissRequest = { }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                options.forEachIndexed { idx, option ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable {
                                onChose(idx)
                            },
                        text = option,
                        style = MaterialTheme.typography.labelSmall
                    )
                    HorizontalDivider()
                }
            }
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = options[selectedIndex],
            style = MaterialTheme.typography.titleSmall
        )
        Icon(
            modifier = Modifier,
            imageVector = Icons.Outlined.ArrowDropDown,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MultipleChoiceSelectShowDialogPreview() {
    MultipleChoiceSelect(
        options = listOf("Best Match", "Most Popular", "Rating"),
        isDialogShow = true,
        selectedIndex = 1,
        onChose = {
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MultipleChoiceSelectHideDialogPreview() {
    MultipleChoiceSelect(
        options = listOf("Best Match", "Most Popular", "Rating"),
        isDialogShow = false,
        selectedIndex = 1,
        onChose = {
        }
    )
}