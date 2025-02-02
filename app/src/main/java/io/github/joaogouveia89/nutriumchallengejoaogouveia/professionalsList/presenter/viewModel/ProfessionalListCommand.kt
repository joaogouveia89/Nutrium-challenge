package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel

sealed class ProfessionalListCommand {
    data object GetProfessionals : ProfessionalListCommand()
    data object DismissError : ProfessionalListCommand()
    data object OnErrorRetry : ProfessionalListCommand()
    data class ChangeFilterType(val filterTypeId: Int) : ProfessionalListCommand()
}