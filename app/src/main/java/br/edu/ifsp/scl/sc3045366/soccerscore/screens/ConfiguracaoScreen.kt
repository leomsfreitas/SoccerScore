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
fun ConfiguracaoScreen(navController: NavController) {
    // rememberSaveable preserva os campos na rotação de tela
    var timeA by rememberSaveable { mutableStateOf("") }
    var timeB by rememberSaveable { mutableStateOf("") }
    var golsA by rememberSaveable { mutableStateOf("") }
    var golsB by rememberSaveable { mutableStateOf("") }
    var erroMensagem by rememberSaveable { mutableStateOf("") }

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
            value = timeA,
            onValueChange = { timeA = it },
            label = { Text("Nome do Time A") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = golsA,
            onValueChange = { golsA = it },
            label = { Text("Gols do Time A") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = timeB,
            onValueChange = { timeB = it },
            label = { Text("Nome do Time B") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = golsB,
            onValueChange = { golsB = it },
            label = { Text("Gols do Time B") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Mensagem de erro de validação
        if (erroMensagem.isNotEmpty()) {
            Text(
                text = erroMensagem,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validação: todos os campos obrigatórios
                when {
                    timeA.isBlank() || timeB.isBlank() || golsA.isBlank() || golsB.isBlank() -> {
                        erroMensagem = "Preencha todos os campos."
                    }
                    golsA.toIntOrNull() == null || golsA.toInt() < 0 -> {
                        erroMensagem = "Gols do Time A deve ser um número inteiro >= 0."
                    }
                    golsB.toIntOrNull() == null || golsB.toInt() < 0 -> {
                        erroMensagem = "Gols do Time B deve ser um número inteiro >= 0."
                    }
                    else -> {
                        erroMensagem = ""
                        val ga = golsA.toInt()
                        val gb = golsB.toInt()
                        // Uri.encode garante que nomes com caracteres especiais não quebrem a rota
                        navController.navigate(
                            "resumo/${Uri.encode(timeA)}/${Uri.encode(timeB)}/$ga/$gb"
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
