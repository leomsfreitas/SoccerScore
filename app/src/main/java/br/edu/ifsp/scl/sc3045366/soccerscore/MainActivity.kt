package br.edu.ifsp.scl.sc3045366.soccerscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.FinalResultScreen
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.MatchSetupScreen
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.MatchSummaryScreen
import br.edu.ifsp.scl.sc3045366.soccerscore.ui.theme.SoccerScoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoccerScoreTheme {
                SoccerScoreApp()
            }
        }
    }
}

@Composable
fun SoccerScoreApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "match-setup",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Screen 1 - Match Setup
            composable("match-setup") {
                MatchSetupScreen(navController = navController)
            }

            // Screen 2 - Match Summary
            // Parameters: team names (String) and goals (Int) via route
            composable(
                route = "match-summary/{teamA}/{teamB}/{goalsA}/{goalsB}",
                arguments = listOf(
                    navArgument("teamA") { type = NavType.StringType },
                    navArgument("teamB") { type = NavType.StringType },
                    navArgument("goalsA") { type = NavType.IntType },
                    navArgument("goalsB") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val teamA = backStackEntry.arguments?.getString("teamA") ?: ""
                val teamB = backStackEntry.arguments?.getString("teamB") ?: ""
                val goalsA = backStackEntry.arguments?.getInt("goalsA") ?: 0
                val goalsB = backStackEntry.arguments?.getInt("goalsB") ?: 0
                MatchSummaryScreen(
                    navController = navController,
                    teamA = teamA,
                    teamB = teamB,
                    goalsA = goalsA,
                    goalsB = goalsB
                )
            }

            // Screen 3 - Final Result
            composable(
                route = "final-result/{teamA}/{teamB}/{goalsA}/{goalsB}",
                arguments = listOf(
                    navArgument("teamA") { type = NavType.StringType },
                    navArgument("teamB") { type = NavType.StringType },
                    navArgument("goalsA") { type = NavType.IntType },
                    navArgument("goalsB") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val teamA = backStackEntry.arguments?.getString("teamA") ?: ""
                val teamB = backStackEntry.arguments?.getString("teamB") ?: ""
                val goalsA = backStackEntry.arguments?.getInt("goalsA") ?: 0
                val goalsB = backStackEntry.arguments?.getInt("goalsB") ?: 0
                FinalResultScreen(
                    navController = navController,
                    teamA = teamA,
                    teamB = teamB,
                    goalsA = goalsA,
                    goalsB = goalsB
                )
            }
        }
    }
}
