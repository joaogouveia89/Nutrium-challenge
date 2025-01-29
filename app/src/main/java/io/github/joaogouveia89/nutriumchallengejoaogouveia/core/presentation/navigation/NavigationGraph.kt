package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PROFESSIONALS_LIST_ROUTE
    ) {
        composable(Routes.PROFESSIONALS_LIST_ROUTE) {
            Text("PROFESSIONALS_LIST_ROUTE")
        }
    }
}