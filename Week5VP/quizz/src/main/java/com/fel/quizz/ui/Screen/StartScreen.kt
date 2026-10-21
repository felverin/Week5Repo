package com.fel.quizz.ui.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fel.quizz.viewmodel.QuizUiState

@Composable
fun StartScreen(
    uiState: QuizUiState,
    onQuestionCountChanged: (String) -> Unit,
    onStartQuizClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Mini Quiz",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C2C2C),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "How many questions do you want? (1 - ${uiState.totalAvailableQuestions})",
                fontSize = 14.sp,
                color = Color(0xFF666666),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = uiState.questionCountInput,
                onValueChange = onQuestionCountChanged,
                label = { Text("Number of questions") },
                placeholder = { Text("Number of questions") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = uiState.isInputError,
                supportingText = {
                    if (uiState.isInputError) {
                        Text(
                            text = uiState.errorMessage,
                            color = Color(0xFFD32F2F)
                        )
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4C5D8B),
                    focusedLabelColor = Color(0xFF4C5D8B),
                    unfocusedBorderColor = Color(0xFFCCCCCC),
                    errorBorderColor = Color(0xFFD32F2F),
                    errorLabelColor = Color(0xFFD32F2F)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            val isButtonEnabled = uiState.questionCountInput.isNotEmpty() && !uiState.isInputError

            Button(
                onClick = onStartQuizClicked,
                enabled = isButtonEnabled,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4C5D8B),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFE5E5E5),
                    disabledContentColor = Color(0xFFA0A0A0)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Start Quiz",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
