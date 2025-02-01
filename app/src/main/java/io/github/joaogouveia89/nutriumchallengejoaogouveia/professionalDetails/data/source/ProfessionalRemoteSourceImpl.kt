package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.source

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalRemoteSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.mapper.asProfessional
import javax.inject.Inject

class ProfessionalRemoteSourceImpl @Inject constructor(
    private val service: NutriumService
): ProfessionalRemoteSource {
    override suspend fun getProfessionalDetails(id: Long): Professional {
        return service.getProfessional(id).asProfessional()
    }
}