package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.data.source

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.domain.source.ProfessionalsRemoteSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.mapper.responseAsProfessionals
import javax.inject.Inject

class ProfessionalsRemoteSourceImpl @Inject constructor(
    private val nutriumService: NutriumService
) : ProfessionalsRemoteSource {
    override suspend fun getProfessionals(filterType: String): List<Professional> {
        return nutriumService
            .getProfessionals(filterType = filterType)
            .responseAsProfessionals()
    }
}