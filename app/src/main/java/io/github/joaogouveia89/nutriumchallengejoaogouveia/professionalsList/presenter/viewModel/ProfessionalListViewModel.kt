package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.GetProfessionalsState
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.repository.ProfessionalsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.state.ProfessionalListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessionalListViewModel @Inject constructor(
    private val professionalsRepository: ProfessionalsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfessionalListUiState())

    val uiState: StateFlow<ProfessionalListUiState>
        get() = _uiState

    fun execute(command: ProfessionalListCommand) {
        when (command) {
            is ProfessionalListCommand.GetProfessionals -> getProfessionals()
            is ProfessionalListCommand.DismissError -> {}
        }
    }

    private fun getProfessionals(){
        viewModelScope.launch {
            professionalsRepository.getProfessionals().collect{ getProfessionalsState ->

                when(getProfessionalsState){
                    is GetProfessionalsState.Loading -> _uiState.update {
                        ProfessionalListUiState(isLoading = true)
                    }
                    is GetProfessionalsState.Success -> _uiState.update {
                        ProfessionalListUiState(
                            professionals = getProfessionalsState.professionals
                        )
                    }

                    GetProfessionalsState.Error -> ProfessionalListUiState()
                }
            }
        }
    }
}