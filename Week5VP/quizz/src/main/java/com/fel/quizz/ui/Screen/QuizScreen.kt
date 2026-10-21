package com.fel.quizz.ui.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
fun QuizScreen(
    uiState: QuizUiState,
    onOptionSelected: (Int) -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val question = uiState.currentQuestion ?: return
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Question ${uiState.currentQuestionIndex + 1}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C2C2C),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF424242),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            question.options.forEachIndexed { index, optionText ->
                val isSelected = uiState.selectedOptionIndex == index

                Surface(
                    onClick = { onOptionSelected(index) },
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) Color(0xFF4C5D8B) else Color(0xFFE8EEFC),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = optionText,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (isSelected) Color.White else Color(0xFF2C2C2C),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onNextClicked,
                enabled = uiState.selectedOptionIndex != null,
                shape = RoundedCornerShape(20.dp),
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
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
