package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.GetProfessionalsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessionalListViewModel @Inject constructor(
    private val professionalsRepository: ProfessionalsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfessionalListUiState())

    private val cachedProfessionals = mutableMapOf<FilterType, List<Professional>>()

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
            is ProfessionalListCommand.GetProfessionals -> viewModelScope.launch {
                getProfessionals()
            }

            is ProfessionalListCommand.ChangeFilterType -> changeFilterType(command.filterTypeId)
            is ProfessionalListCommand.DismissError -> {}
        }
    }

    private suspend fun getProfessionals() {
        professionalsRepository
            .getProfessionals(
                filterType = _uiState.value.filterType.apiIdentifier
            )
            .map(::getProfessionalsToUiState)
            .collect { getProfessionalsState ->
                getProfessionalsState.professionals?.let {
                    //updating cache
                    cachedProfessionals[_uiState.value.filterType] = getProfessionalsState.professionals
                }
                _uiState.update { getProfessionalsState }
            }
    }

    private fun changeFilterType(filterTypeId: Int) {
        val filterType = filterTypes.firstOrNull {
            it.ordinal == filterTypeId
        }
        filterType?.let { ft ->
            _uiState.update { it.copy(filterType = ft) }

            val cacheData = cachedProfessionals[_uiState.value.filterType]

            if(cacheData == null){
                viewModelScope.launch {
                    getProfessionals()
                }
            }else{
                _uiState.update {
                    it.copy(
                        professionals = cacheData
                    )
                }
            }
        }
    }

    private fun getProfessionalsToUiState(getProfessionalsState: GetProfessionalsState): ProfessionalListUiState {
        return when (getProfessionalsState) {
            is GetProfessionalsState.Loading -> _uiState.value.copy(isLoading = true)
            is GetProfessionalsState.Success -> _uiState.value.copy(
                professionals = getProfessionalsState.professionals,
                isLoading = false
            )

            is GetProfessionalsState.Error -> ProfessionalListUiState()
        }
    }
}