package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation.ProfessionalDetailsNav.professionalIdIdentifier
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.repository.ProfessionalDetailsRepository
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.state.ProfessionalDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfessionalDetailsViewModel @Inject constructor(
    private val repository: ProfessionalDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val professionalId = savedStateHandle.get<Long>(key = professionalIdIdentifier)

    private val _uiState = MutableStateFlow(ProfessionalDetailsUiState())

    val uiState: StateFlow<ProfessionalDetailsUiState>
        get() = _uiState
}