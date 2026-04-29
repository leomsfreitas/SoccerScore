package br.edu.ifsp.scl.sc3045366.soccerscore.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Tela 1: coleta os dados da partida e navega para o Resumo
@Composable
fun MatchSetupScreen(navController: NavController) {
    // rememberSaveable preserva os campos na rotação de tela
    var teamA by rememberSaveable { mutableStateOf("") }
    var teamB by rememberSaveable { mutableStateOf("") }
    var goalsA by rememberSaveable { mutableStateOf("") }
    var goalsB by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SoccerScore",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Configuração da Partida",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = teamA,
            onValueChange = { teamA = it },
            label = { Text("Nome do Time A") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = goalsA,
            onValueChange = { goalsA = it },
            label = { Text("Gols do Time A") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = teamB,
            onValueChange = { teamB = it },
            label = { Text("Nome do Time B") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = goalsB,
            onValueChange = { goalsB = it },
            label = { Text("Gols do Time B") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensagem de erro de validação
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validação: todos os campos obrigatórios
                when {
                    teamA.isBlank() || teamB.isBlank() || goalsA.isBlank() || goalsB.isBlank() -> {
                        errorMessage = "Preencha todos os campos."
                    }
                    goalsA.toIntOrNull() == null || goalsA.toInt() < 0 -> {
                        errorMessage = "Gols do Time A deve ser um número inteiro >= 0."
                    }
                    goalsB.toIntOrNull() == null || goalsB.toInt() < 0 -> {
                        errorMessage = "Gols do Time B deve ser um número inteiro >= 0."
                    }
                    else -> {
                        errorMessage = ""
                        val ga = goalsA.toInt()
                        val gb = goalsB.toInt()
                        // Uri.encode garante que nomes com caracteres especiais não quebrem a rota
                        navController.navigate(
                            "match-summary/${Uri.encode(teamA)}/${Uri.encode(teamB)}/$ga/$gb"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Resultado")
        }
    }
}
