package io.github.joaogouveia89.nutriumchallengejoaogouveia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.joaogouveia89.nutriumchallengejoaogouveia.core.presentation.MainScreen
import io.github.joaogouveia89.nutriumchallengejoaogouveia.ui.theme.NutriumChallengeJoaoGouveiaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriumChallengeJoaoGouveiaTheme {
                MainScreen(rememberNavController())
            }
        }
    }
}