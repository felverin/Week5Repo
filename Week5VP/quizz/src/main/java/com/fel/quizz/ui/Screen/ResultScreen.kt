package com.fel.quizz.ui.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fel.quizz.viewmodel.QuizUiState

@Composable
fun ResultScreen(
    uiState: QuizUiState,
    onRestartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Quiz Finished!",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C2C2C),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your Score: ${uiState.score} / ${uiState.totalQuizQuestions}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF555555),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.score == uiState.totalQuizQuestions) {
                Text(
                    text = "Perfect! No wrong answers.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4C5D8B),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.wrongAnswers) { wrongAnswer ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "Q${wrongAnswer.questionNumber}: ${wrongAnswer.question.text}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF333333)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Your Answer: ${wrongAnswer.userSelectedText}",
                                fontSize = 13.sp,
                                color = Color(0xFFC62828)
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "Correct Answer: ${wrongAnswer.correctAnswerText}",
                                fontSize = 13.sp,
                                color = Color(0xFF4C5D8B)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            HorizontalDivider(
                                color = Color(0xFFE0E0E0),
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onRestartClicked,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4C5D8B),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Restart Quiz",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
