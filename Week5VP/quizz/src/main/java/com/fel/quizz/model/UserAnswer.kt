package com.fel.quizz.model

data class UserAnswer(
    val questionNumber: Int,
    val question: Question,
    val selectedOptionIndex: Int
) {
    val isCorrect: Boolean
        get() = selectedOptionIndex == question.correctAnswerIndex

    val userSelectedText: String
        get() = question.options[selectedOptionIndex]

    val correctAnswerText: String
        get() = question.options[question.correctAnswerIndex]
}
