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
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.ConfiguracaoScreen
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.ResumoScreen
import br.edu.ifsp.scl.sc3045366.soccerscore.screens.ResultadoScreen
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
            startDestination = "configuracao",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Tela 1 – Configuração da Partida
            composable("configuracao") {
                ConfiguracaoScreen(navController = navController)
            }

            // Tela 2 – Resumo da Partida
            // Parâmetros: nomes dos times (String) e gols (Int) via rota
            composable(
                route = "resumo/{timeA}/{timeB}/{golsA}/{golsB}",
                arguments = listOf(
                    navArgument("timeA") { type = NavType.StringType },
                    navArgument("timeB") { type = NavType.StringType },
                    navArgument("golsA") { type = NavType.IntType },
                    navArgument("golsB") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val timeA = backStackEntry.arguments?.getString("timeA") ?: ""
                val timeB = backStackEntry.arguments?.getString("timeB") ?: ""
                val golsA = backStackEntry.arguments?.getInt("golsA") ?: 0
                val golsB = backStackEntry.arguments?.getInt("golsB") ?: 0
                ResumoScreen(
                    navController = navController,
                    timeA = timeA,
                    timeB = timeB,
                    golsA = golsA,
                    golsB = golsB
                )
            }

            // Tela 3 – Resultado Final
            composable(
                route = "resultado/{timeA}/{timeB}/{golsA}/{golsB}",
                arguments = listOf(
                    navArgument("timeA") { type = NavType.StringType },
                    navArgument("timeB") { type = NavType.StringType },
                    navArgument("golsA") { type = NavType.IntType },
                    navArgument("golsB") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val timeA = backStackEntry.arguments?.getString("timeA") ?: ""
                val timeB = backStackEntry.arguments?.getString("timeB") ?: ""
                val golsA = backStackEntry.arguments?.getInt("golsA") ?: 0
                val golsB = backStackEntry.arguments?.getInt("golsB") ?: 0
                ResultadoScreen(
                    navController = navController,
                    timeA = timeA,
                    timeB = timeB,
                    golsA = golsA,
                    golsB = golsB
                )
            }
        }
    }
}
