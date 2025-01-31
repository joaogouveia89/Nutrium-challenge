package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model

import com.google.gson.annotations.SerializedName

data class ProfessionalsSearchResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("professionals") val professionals: List<ProfessionalRemote>,
)