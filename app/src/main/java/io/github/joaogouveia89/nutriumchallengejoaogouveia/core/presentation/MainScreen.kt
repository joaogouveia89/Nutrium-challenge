package io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.navigation.NavigationGraph

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {

        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController = navController)
            }
        }
    )
}