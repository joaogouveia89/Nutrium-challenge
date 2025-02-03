package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model

data class Professional(
    val id: Long = 0,
    val name: String = "",
    val profilePictureUrl: String = "",
    val rating: Int = 0,
    val ratingCount: Int = 0,
    val aboutMe: String = "",
    val expertise: List<String> = listOf(),
    val languages: List<String> = listOf(),
) {
    val nameInitials: String
        get() = if (name.isNotEmpty())
            name
                .split(" ")
                .map { it.first() }
                .joinToString("")
        else ""
}