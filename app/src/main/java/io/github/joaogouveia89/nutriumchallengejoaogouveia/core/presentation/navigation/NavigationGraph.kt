package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.ProfessionalDetailsScreen
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.viewModel.ProfessionalDetailsCommand
import io.github.joaogouveia89.nutriumchallengejoaogouveia.professionalDetails.presenter.viewModel.ProfessionalDetailsViewModel
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
                uiState = uiState,
                filterTypesEntries = viewModel.filterTypesEntries,
                onProfessionalClick = {
                    navController.navigate(ProfessionalDetailsNav.passProfessionalId(professionalId = it.id))
                }
            )
        }

        composable(
            ProfessionalDetailsNav.route,
            arguments = listOf(
                navArgument(ProfessionalDetailsNav.professionalIdIdentifier) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) {
            val viewModel: ProfessionalDetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            // Added this variable here to be easier to change to view model control in the future if it makes sense
            var isAboutMeExpanded by rememberSaveable { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                viewModel.execute(ProfessionalDetailsCommand.GetProfessionalDetails)
            }

            ProfessionalDetailsScreen(
                uiState = uiState,
                isAboutMeExpanded = isAboutMeExpanded,
                onAboutMeExpandCollapseClick = { isAboutMeExpanded = !isAboutMeExpanded },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}