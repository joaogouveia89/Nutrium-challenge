package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfessionalListViewModel @Inject constructor(
    private val professionalsRepository: ProfessionalsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfessionalListUiState())

    val uiState: StateFlow<ProfessionalListUiState>
        get() = _uiState

    private val filterTypes = FilterType
        .getFilters()

    val filterTypesHumanized = filterTypes
        .map { ft ->
            ft.name
                .lowercase()
                .replace("_", " ")
                .split(" ")
                .joinToString(" ") { it.replaceFirstChar(Char::titlecase) }
        }

    fun execute(command: ProfessionalListCommand) {
        when (command) {
            is ProfessionalListCommand.GetProfessionals -> getProfessionals()
            is ProfessionalListCommand.ChangeFilterType -> changeFilterType(command.filterTypeId)
            is ProfessionalListCommand.DismissError -> {}
        }
    }

    private fun getProfessionals() {
        _uiState.update { it.copy(isLoading = true) }

        val professionalsPagingFlow = professionalsRepository.getProfessionals(
            filterType = _uiState.value.filterType.apiIdentifier,
            pagingConfig = PagingConfig(pageSize = 4, initialLoadSize = 4)
        ).cachedIn(viewModelScope)

        _uiState.update {
            it.copy(
                professionals = professionalsPagingFlow,
                isLoading = false
            )
        }

    }

    private fun changeFilterType(filterTypeId: Int) {
        val filterType = filterTypes.firstOrNull {
            it.ordinal == filterTypeId
        }
        filterType?.let { ft ->
            _uiState.update { it.copy(filterType = ft) }
            getProfessionals()
        }
    }
}