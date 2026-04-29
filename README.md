# SoccerScore

Aplicativo Android de Simulador de Placar de Futebol desenvolvido com Jetpack Compose e Navigation Compose.

## Funcionalidades

- **Tela 1 – Configuração da Partida**: inserção dos nomes dos times e quantidade de gols, com validação de campos.
- **Tela 2 – Resumo da Partida**: exibe o placar antes da confirmação, com opção de editar ou confirmar.
- **Tela 3 – Resultado Final**: exibe o vencedor ou empate, com botão para iniciar novo jogo.

## Tecnologias

- Kotlin
- Jetpack Compose
- Navigation Compose (passagem de parâmetros via rotas)
- `rememberSaveable` para preservação de estado na rotação de tela

## Como executar

1. Abra o projeto no Android Studio.
2. Sincronize as dependências Gradle.
3. Execute em um emulador ou dispositivo físico com Android 8.0+ (API 26).

## Estrutura do projeto

```
app/src/main/java/.../soccerscore/
├── MainActivity.kt           # NavHost com as 3 rotas
└── screens/
    ├── ConfiguracaoScreen.kt # Tela 1
    ├── ResumoScreen.kt       # Tela 2
    └── ResultadoScreen.kt    # Tela 3
```

