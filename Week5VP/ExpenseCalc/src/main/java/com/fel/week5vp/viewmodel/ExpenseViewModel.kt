package com.fel.week5vp.viewmodel

import androidx.lifecycle.ViewModel
import com.fel.week5vp.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ExpenseUiState(
    val expenses: List<Expense> = emptyList(),
    val expenseNameInput: String = "",
    val expenseAmountInput: String = "",
    val errorMessage: String? = null,
    val expenseToEdit: Expense? = null,
    val editNameInput: String = "",
    val editAmountInput: String = "",
    val editErrorMessage: String? = null
) {
    val totalExpense: Long
        get() = expenses.sumOf { it.amount }
}

class ExpenseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ExpenseUiState(
            expenses = listOf(
                Expense(name = "Makan Nasi", amount = 90000),
                Expense(name = "Investasi", amount = 10000000),
                Expense(name = "Kopi", amount = 18000)
            )
        )
    )
    val uiState: StateFlow<ExpenseUiState> = _uiState.asStateFlow()

    fun onNameInputChanged(name: String) {
        _uiState.update { currentState ->
            currentState.copy(expenseNameInput = name)
        }
    }

    fun onAmountInputChanged(amount: String) {
        _uiState.update { currentState ->
            currentState.copy(
                expenseAmountInput = amount,
                errorMessage = null
            )
        }
    }

    fun addExpense() {
        val name = _uiState.value.expenseNameInput.trim()
        val amountStr = _uiState.value.expenseAmountInput.trim()
        val amount = amountStr.toLongOrNull()

        if (name.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Expense name cannot be empty") }
            return
        }

        if (amount == null || amount <= 0) {
            _uiState.update { it.copy(errorMessage = "Amount must be greater than 0") }
            return
        }

        val newExpense = Expense(name = name, amount = amount)
        _uiState.update { currentState ->
            currentState.copy(
                expenses = currentState.expenses + newExpense,
                expenseNameInput = "",
                expenseAmountInput = "",
                errorMessage = null
            )
        }
    }

    fun deleteExpense(expense: Expense) {
        _uiState.update { currentState ->
            currentState.copy(
                expenses = currentState.expenses.filterNot { it.id == expense.id }
            )
        }
    }

    fun startEditing(expense: Expense) {
        _uiState.update { currentState ->
            currentState.copy(
                expenseToEdit = expense,
                editNameInput = expense.name,
                editAmountInput = expense.amount.toString(),
                editErrorMessage = null
            )
        }
    }

    fun onEditNameChanged(name: String) {
        _uiState.update { currentState ->
            currentState.copy(editNameInput = name)
        }
    }

    fun onEditAmountChanged(amount: String) {
        _uiState.update { currentState ->
            currentState.copy(editAmountInput = amount, editErrorMessage = null)
        }
    }

    fun cancelEditing() {
        _uiState.update { currentState ->
            currentState.copy(
                expenseToEdit = null,
                editNameInput = "",
                editAmountInput = "",
                editErrorMessage = null
            )
        }
    }

    fun saveEdit() {
        val expenseToEdit = _uiState.value.expenseToEdit ?: return
        val name = _uiState.value.editNameInput.trim()
        val amountStr = _uiState.value.editAmountInput.trim()
        val amount = amountStr.toLongOrNull()

        if (name.isEmpty()) {
            _uiState.update { it.copy(editErrorMessage = "Expense name cannot be empty") }
            return
        }

        if (amount == null || amount <= 0) {
            _uiState.update { it.copy(editErrorMessage = "Amount must be greater than 0") }
            return
        }

        _uiState.update { currentState ->
            val updatedList = currentState.expenses.map { item ->
                if (item.id == expenseToEdit.id) {
                    item.copy(name = name, amount = amount)
                } else {
                    item
                }
            }
            currentState.copy(
                expenses = updatedList,
                expenseToEdit = null,
                editNameInput = "",
                editAmountInput = "",
                editErrorMessage = null
            )
        }
    }
}
