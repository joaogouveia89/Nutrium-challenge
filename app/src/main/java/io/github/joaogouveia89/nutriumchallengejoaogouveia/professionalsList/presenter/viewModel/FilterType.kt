package io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel

enum class FilterType {
    BEST_MATCH,
    MOST_POPULAR,
    RATING;

    val apiIdentifier: String
        get() = name.lowercase()

    companion object{
        fun getFilters() = entries.toList()
    }
}