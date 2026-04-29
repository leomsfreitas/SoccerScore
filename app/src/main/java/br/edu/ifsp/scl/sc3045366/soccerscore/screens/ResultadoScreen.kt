package br.edu.ifsp.scl.sc3045366.soccerscore.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Tela 3: exibe o resultado final da partida
@Composable
fun ResultadoScreen(
    navController: NavController,
    timeA: String,
    timeB: String,
    golsA: Int,
    golsB: Int
) {
    // Regra de negócio: determina o vencedor ou empate
    val resultado = when {
        golsA > golsB -> "$timeA venceu!"
        golsB > golsA -> "$timeB venceu!"
        else -> "Empate emocionante!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultado Final",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "$timeA  $golsA x $golsB  $timeB",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = resultado,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {
                // Volta para a Tela 1 limpando a pilha de navegação
                navController.popBackStack("configuracao", inclusive = false)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Novo Jogo")
        }
    }
}
