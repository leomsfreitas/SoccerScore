# SoccerScore

Android Soccer Score Simulator developed with Jetpack Compose and Navigation Compose.

## Features

- **Screen 1 – Match Setup**: input fields for team names and goal counts, with field validation.
- **Screen 2 – Match Summary**: displays the scoreboard before confirmation, with options to edit or confirm.
- **Screen 3 – Final Result**: shows the winner or a draw, with a button to start a new game.

## Technologies

- Kotlin
- Jetpack Compose
- Navigation Compose (parameter passing via routes)
- `rememberSaveable` for state preservation on screen rotation

## How to Run

1. Open the project in Android Studio.
2. Sync Gradle dependencies.
3. Run on an emulator or physical device with Android 8.0+ (API 26).

## Project Structure

```
app/src/main/java/.../soccerscore/
├── MainActivity.kt           # NavHost with the 3 routes
└── screens/
    ├── ConfiguracaoScreen.kt # Screen 1
    ├── ResumoScreen.kt       # Screen 2
    └── ResultadoScreen.kt    # Screen 3
```
