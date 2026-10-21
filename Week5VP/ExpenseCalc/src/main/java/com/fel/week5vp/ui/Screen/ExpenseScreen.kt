package com.fel.week5vp.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fel.week5vp.model.Expense
import com.fel.week5vp.viewmodel.ExpenseViewModel
import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getInstance(Locale.forLanguageTag("id-ID"))
    return formatter.format(amount)
}

@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    viewModel: ExpenseViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header
            Text(
                text = "Expenses",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1B20)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Total Expense : Rp ${formatRupiah(uiState.totalExpense)}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF49454F)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Input Fields
            OutlinedTextField(
                value = uiState.expenseNameInput,
                onValueChange = viewModel::onNameInputChanged,
                placeholder = { Text("Expense Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = uiState.expenseAmountInput,
                        onValueChange = viewModel::onAmountInputChanged,
                        placeholder = { Text("Amount of Expense") },
                        singleLine = true,
                        isError = uiState.errorMessage != null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            errorContainerColor = Color.White
                        )
                    )

                    if (uiState.errorMessage != null) {
                        Text(
                            text = uiState.errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE4E4EC),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { viewModel.addExpense() }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Expense",
                            tint = Color(0xFF555555),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Expense Items List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(uiState.expenses, key = { it.id }) { expense ->
                    ExpenseCard(
                        expense = expense,
                        onEdit = { viewModel.startEditing(expense) },
                        onDelete = { viewModel.deleteExpense(expense) }
                    )
                }
            }
        }

        // Bottom Left Menu FAB
        FloatingActionButton(
            onClick = { /* Menu action if needed */ },
            shape = CircleShape,
            containerColor = Color.White,
            contentColor = Color(0xFF1D1B20),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp)
            )
        }

        // Edit Expense Dialog
        if (uiState.expenseToEdit != null) {
            AlertDialog(
                onDismissRequest = { viewModel.cancelEditing() },
                title = {
                    Text(
                        text = "Edit Expense",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                text = {
                    Column {
                        OutlinedTextField(
                            value = uiState.editNameInput,
                            onValueChange = viewModel::onEditNameChanged,
                            label = { Text("Expense Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = uiState.editAmountInput,
                            onValueChange = viewModel::onEditAmountChanged,
                            label = { Text("Amount of Expense") },
                            singleLine = true,
                            isError = uiState.editErrorMessage != null,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (uiState.editErrorMessage != null) {
                            Text(
                                text = uiState.editErrorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { viewModel.saveEdit() }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.cancelEditing() }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun ExpenseCard(
    expense: Expense,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3E7FA)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Name : ${expense.name}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF232538)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Rp ${formatRupiah(expense.amount)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF535670)
                )
            }

            IconButton(onClick = onEdit) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color(0xFFC58F00),
                    modifier = Modifier.size(20.dp)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFC62828),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
