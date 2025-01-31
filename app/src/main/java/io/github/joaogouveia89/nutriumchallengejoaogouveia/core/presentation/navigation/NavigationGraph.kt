package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.ProfessionalListScreen
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.ProfessionalListCommand
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalsList.presenter.viewModel.ProfessionalListViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PROFESSIONALS_LIST_ROUTE
    ) {
        composable(Routes.PROFESSIONALS_LIST_ROUTE) {
            val viewModel: ProfessionalListViewModel = hiltViewModel()

            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.execute(ProfessionalListCommand.GetProfessionals)
            }

            ProfessionalListScreen(
                uiState = uiState
            )
        }
    }
}