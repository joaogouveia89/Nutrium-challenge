package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model

data class Professional(
    val id: Long,
    val name: String,
    val profilePictureUrl: String,
    val rating: Int,
    val ratingCount: Int,
    val aboutMe: String,
    val expertise: List<String>,
    val languages: List<String>,
)