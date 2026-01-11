package com.financeme.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [BudgetProfileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun budgetProfileDao(): BudgetProfileDao

    companion object {
        fun buildEncrypted(context: Context, passphrase: CharArray): AppDatabase {
            val factory = SupportFactory(SQLiteDatabase.getBytes(passphrase))
            return Room.databaseBuilder(context, AppDatabase::class.java, "financeme.db")
                .openHelperFactory(factory)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

