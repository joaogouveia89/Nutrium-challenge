package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalResponse
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalsSearchResponse
import retrofit2.http.GET

interface NutriumService {
    // TODO Add pagination
    @GET("/professionals/search?limit=4&offset=0&sort_by=best_match")
    suspend fun getProfessionals(
    ): ProfessionalsSearchResponse

    @GET("/professionals/{id}")
    suspend fun getProfessional(id: Long
    ): ProfessionalResponse
}