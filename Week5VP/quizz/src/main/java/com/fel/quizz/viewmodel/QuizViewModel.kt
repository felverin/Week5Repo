package com.fel.quizz.viewmodel

import androidx.lifecycle.ViewModel
import com.fel.quizz.model.Question
import com.fel.quizz.data.QuestionRepository
import com.fel.quizz.model.UserAnswer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class ScreenState {
    START,
    QUIZ,
    RESULT
}

data class QuizUiState(
    val currentScreen: ScreenState = ScreenState.START,
    val questionCountInput: String = "",
    val isInputError: Boolean = false,
    val errorMessage: String = "",
    val totalAvailableQuestions: Int = 25,
    val selectedQuestions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val userAnswers: List<UserAnswer> = emptyList(),
    val totalQuizQuestions: Int = 0
) {
    val currentQuestion: Question?
        get() = selectedQuestions.getOrNull(currentQuestionIndex)

    val score: Int
        get() = userAnswers.count { it.isCorrect }

    val wrongAnswers: List<UserAnswer>
        get() = userAnswers.filter { !it.isCorrect }
}

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    fun onQuestionCountChanged(input: String) {
        val number = input.toIntOrNull()
        val maxAvailable = QuestionRepository.questions.size

        when {
            input.isEmpty() -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        questionCountInput = input,
                        isInputError = false,
                        errorMessage = ""
                    )
                }
            }
            number == null -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        questionCountInput = input,
                        isInputError = true,
                        errorMessage = "Please enter a valid number"
                    )
                }
            }
            number <= 0 -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        questionCountInput = input,
                        isInputError = true,
                        errorMessage = "Number must be at least 1"
                    )
                }
            }
            number > maxAvailable -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        questionCountInput = input,
                        isInputError = true,
                        errorMessage = "Maximum is $maxAvailable questions"
                    )
                }
            }
            else -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        questionCountInput = input,
                        isInputError = false,
                        errorMessage = ""
                    )
                }
            }
        }
    }

    fun startQuiz() {
        val currentState = _uiState.value
        val count = currentState.questionCountInput.toIntOrNull() ?: return
        val maxAvailable = QuestionRepository.questions.size

        if (count in 1..maxAvailable && !currentState.isInputError) {
            val randomQuestions = QuestionRepository.questions.shuffled().take(count)

            _uiState.update { state ->
                state.copy(
                    currentScreen = ScreenState.QUIZ,
                    selectedQuestions = randomQuestions,
                    currentQuestionIndex = 0,
                    selectedOptionIndex = null,
                    userAnswers = emptyList(),
                    totalQuizQuestions = count
                )
            }
        }
    }

    fun selectOption(optionIndex: Int) {
        _uiState.update { currentState ->
            currentState.copy(selectedOptionIndex = optionIndex)
        }
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        val currentQ = currentState.currentQuestion ?: return
        val selectedIdx = currentState.selectedOptionIndex ?: return

        val newAnswer = UserAnswer(
            questionNumber = currentState.currentQuestionIndex + 1,
            question = currentQ,
            selectedOptionIndex = selectedIdx
        )

        val updatedAnswers = currentState.userAnswers + newAnswer
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex < currentState.selectedQuestions.size) {
            _uiState.update { state ->
                state.copy(
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    userAnswers = updatedAnswers
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    currentScreen = ScreenState.RESULT,
                    userAnswers = updatedAnswers
                )
            }
        }
    }

    fun restartQuiz() {
        _uiState.update {
            QuizUiState(
                currentScreen = ScreenState.START,
                totalAvailableQuestions = QuestionRepository.questions.size
            )
        }
    }
}
