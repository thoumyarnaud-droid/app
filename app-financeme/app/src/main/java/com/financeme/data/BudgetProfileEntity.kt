package com.financeme.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_profile")
data class BudgetProfileEntity(
    @PrimaryKey val id: Long = 0,
    val currencyCode: String = "EUR",
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

