package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.ProfessionalListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PROFESSIONALS_LIST_ROUTE
    ) {
        composable(Routes.PROFESSIONALS_LIST_ROUTE) {
            ProfessionalListScreen()
        }
    }
}