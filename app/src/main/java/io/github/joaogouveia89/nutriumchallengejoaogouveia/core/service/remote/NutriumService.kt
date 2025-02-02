package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalResponse
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NutriumService {
    // TODO Add pagination
    @GET("/professionals/search?limit=4&offset=0")
    suspend fun getProfessionals(
        @Query("sort_by") filterType: String
    ): ProfessionalsSearchResponse

    @GET("/professionals/{id}")
    suspend fun getProfessional(@Path("id") id: Long): ProfessionalResponse
}