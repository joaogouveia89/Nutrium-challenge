package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.joaogouveia89.nutriumchallengejoaogouveia.R
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.GenericErrorScreen
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.ErrorSnackBar
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.components.MultipleChoiceSelect
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.previews.filterTypeEntries
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.previews.professionalsPageFlow
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.components.ProfessionalListItem
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.FilterType

@Composable
fun ProfessionalListContent(
    professionals: LazyPagingItems<Professional>,
    refreshState: LoadState,
    appendState: LoadState,
    currentFilterType: FilterType,
    filterTypesEntries: List<String>,
    onProfessionalClick: (Professional) -> Unit,
    onErrorRetryClick: () -> Unit,
    onFilterTypeSelected: (Int) -> Unit
) {
    var isDialogShow by remember { mutableStateOf(false) }

    val isLoadingRefresh = refreshState is LoadState.Loading
    val isAppending = appendState is LoadState.Loading

    val isErrorRefresh = refreshState is LoadState.Error
    val isErrorAppending = appendState is LoadState.Error

    if (isErrorRefresh) {
        GenericErrorScreen(
            errorMessage = stringResource(R.string.professional_list_error_loading),
            onRetryClick = onErrorRetryClick
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            ) {
                MultipleChoiceSelect(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(0.9f)
                        .border(width = 1.dp, color = Black, shape = RectangleShape)
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable { isDialogShow = true },
                    options = filterTypesEntries,
                    isDialogShow = isDialogShow,
                    selectedIndex = currentFilterType.ordinal,
                    onChose = {
                        onFilterTypeSelected(it)
                        isDialogShow = false
                    }
                )

                if (isLoadingRefresh) {
                    LoadingContent()
                } else {
                    ListContent(
                        professionalsPaging = professionals,
                        onProfessionalClick = onProfessionalClick,
                        isAppending = isAppending
                    )
                }
            }
            if (isErrorAppending) {
                ErrorSnackBar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(R.string.professional_list_error_appending),
                )
            }
        }
    }
}

@Composable
private fun ListContent(
    professionalsPaging: LazyPagingItems<Professional>,
    onProfessionalClick: (Professional) -> Unit,
    isAppending: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 18.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(professionalsPaging.itemCount) { index ->
            val professional = professionalsPaging[index]

            professional?.let {
                ProfessionalListItem(
                    it,
                    onProfessionalClick = onProfessionalClick
                )
            }
        }
        if (isAppending) {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentPreview() {

    val professionals = professionalsPageFlow.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = LoadState.NotLoading(true),
        appendState = professionals.loadState.append,
        currentFilterType = FilterType.BEST_MATCH,
        onProfessionalClick = {},
        filterTypesEntries = filterTypeEntries,
        onFilterTypeSelected = {},
        onErrorRetryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentLoadingRefreshPreview() {

    val professionals = professionalsPageFlow.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = LoadState.Loading,
        appendState = LoadState.NotLoading(false),
        currentFilterType = FilterType.BEST_MATCH,
        onProfessionalClick = {},
        filterTypesEntries = filterTypeEntries,
        onFilterTypeSelected = {},
        onErrorRetryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentLoadingAppendPreview() {

    val professionals = professionalsPageFlow.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = LoadState.NotLoading(true),
        appendState = LoadState.Loading,
        currentFilterType = FilterType.BEST_MATCH,
        onProfessionalClick = {},
        filterTypesEntries = filterTypeEntries,
        onFilterTypeSelected = {},
        onErrorRetryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentErrorRefreshPreview() {

    val professionals = professionalsPageFlow.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = LoadState.Error(Throwable()),
        appendState = LoadState.NotLoading(true),
        currentFilterType = FilterType.BEST_MATCH,
        onProfessionalClick = {},
        filterTypesEntries = filterTypeEntries,
        onFilterTypeSelected = {},
        onErrorRetryClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfessionalListContentErrorAppendPreview() {

    val professionals = professionalsPageFlow.collectAsLazyPagingItems()

    ProfessionalListContent(
        professionals = professionals,
        refreshState = LoadState.NotLoading(true),
        appendState = LoadState.Error(Throwable()),
        currentFilterType = FilterType.BEST_MATCH,
        onProfessionalClick = {},
        filterTypesEntries = filterTypeEntries,
        onFilterTypeSelected = {},
        onErrorRetryClick = {}
    )
}