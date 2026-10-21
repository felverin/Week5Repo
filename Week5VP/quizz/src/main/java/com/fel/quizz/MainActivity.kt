package com.fel.quizz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fel.quizz.navigation.QuizRoute
import com.fel.quizz.navigation.ResultRoute
import com.fel.quizz.navigation.StartRoute
import com.fel.quizz.ui.Screen.QuizScreen
import com.fel.quizz.ui.Screen.ResultScreen
import com.fel.quizz.ui.Screen.StartScreen
import com.fel.quizz.ui.theme.Week5VPTheme
import com.fel.quizz.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week5VPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuizApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuizApp(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel = viewModel()
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = StartRoute,
        modifier = modifier
    ) {
        composable<StartRoute> {
            StartScreen(
                uiState = uiState,
                onQuestionCountChanged = { input -> viewModel.onQuestionCountChanged(input) },
                onStartQuizClicked = {
                    viewModel.startQuiz()
                    navController.navigate(QuizRoute)
                }
            )
        }

        composable<QuizRoute> {
            QuizScreen(
                uiState = uiState,
                onOptionSelected = { index -> viewModel.selectOption(index) },
                onNextClicked = {
                    val isLastQuestion = uiState.currentQuestionIndex + 1 >= uiState.selectedQuestions.size
                    viewModel.nextQuestion()
                    if (isLastQuestion) {
                        navController.navigate(ResultRoute) {
                            popUpTo<StartRoute>()
                        }
                    }
                }
            )
        }

        composable<ResultRoute> {
            ResultScreen(
                uiState = uiState,
                onRestartClicked = {
                    viewModel.restartQuiz()
                    navController.navigate(StartRoute) {
                        popUpTo<StartRoute> { inclusive = true }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizPreview() {
    Week5VPTheme {
        QuizApp()
    }
}
