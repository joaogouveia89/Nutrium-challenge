package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.data.source

import android.util.Log
import io.github.joaogouveia89.nutriumchallengejoaogouveia.BuildConfig
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.NutriumService
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.domain.source.ProfessionalDetailsRemoteSource
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.mapper.asProfessional
import javax.inject.Inject

class ProfessionalDetailsRemoteSourceImpl @Inject constructor(
    private val service: NutriumService
) : ProfessionalDetailsRemoteSource {
    override suspend fun getProfessionalDetails(id: Long): Professional? {
        return try {
            service.getProfessional(id).asProfessional()
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, exception.toString())
            }
            null
        }
    }

    companion object {
        val TAG: String = this::class.java.declaringClass.simpleName
    }
}