package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.mapper

import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalResponse
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.service.remote.model.ProfessionalsSearchResponse

fun ProfessionalsSearchResponse.responseAsProfessionals(): List<Professional> =
    this.professionals.map {
        it.asProfessional()
    }

fun ProfessionalResponse.asProfessional(): Professional =
    Professional(
        id = id,
        name = name,
        profilePictureUrl = profilePictureUrl,
        rating = rating,
        ratingCount = ratingCount,
        aboutMe = aboutMe,
        expertise = expertise,
        languages = languages
    )