package com.fel.week5vp.model

import java.util.UUID

data class Expense(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val amount: Long
)
