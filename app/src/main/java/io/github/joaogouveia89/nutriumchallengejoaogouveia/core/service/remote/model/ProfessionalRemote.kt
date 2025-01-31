package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model

import com.google.gson.annotations.SerializedName

data class ProfessionalRemote(
    @SerializedName("about_me") val aboutMe: String,
    @SerializedName("expertise") val expertise: List<String>,
    @SerializedName("id") val id: Long,
    @SerializedName("languages") val languages: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("profile_picture_url") val profilePictureUrl: String,
    @SerializedName("rating") val rating: Int,
    @SerializedName("rating_count") val ratingCount: Int,
)