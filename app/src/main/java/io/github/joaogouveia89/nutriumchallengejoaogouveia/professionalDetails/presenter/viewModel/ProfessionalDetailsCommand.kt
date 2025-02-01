package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.viewModel

sealed class ProfessionalDetailsCommand {
    data object GetProfessionalDetails : ProfessionalDetailsCommand()
}
