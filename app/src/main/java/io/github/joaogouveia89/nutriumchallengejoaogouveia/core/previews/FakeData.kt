package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.previews

import androidx.paging.PagingData
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.model.Professional
import kotlinx.coroutines.flow.flowOf

val filterTypeEntries = listOf("Best Match", "Most Popular", "Rating")

val singleProfessional =  Professional(
    id = 1,
    name = "John",
    profilePictureUrl = "",
    rating = 3,
    ratingCount = 21,
    aboutMe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vulputate vestibulum velit, ultricies ultrices urna varius ut. Quisque imperdiet consequat dui, ut sodales enim sodales ornare. Aliquam leo magna, sagittis ac enim a, luctus scelerisque orci. Duis ligula neque, vehicula.",
    expertise = listOf("exp1", "exp2", "exp3"),
    languages = listOf("lan1", "lan2", "lan3")
)

val professionalsPageFlow = flowOf(
    PagingData.from(
        listOf(singleProfessional, singleProfessional, singleProfessional)
    )
)