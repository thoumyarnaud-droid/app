package com.financeme.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BudgetProfileDao {
    @Query("SELECT * FROM budget_profile WHERE id = 0 LIMIT 1")
    suspend fun get(): BudgetProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: BudgetProfileEntity)
}

