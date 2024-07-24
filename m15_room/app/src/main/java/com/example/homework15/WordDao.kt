package com.example.homework15

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word LIMIT 5")
    fun getAll(): Flow<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("UPDATE word SET repetitions = repetitions + 1 WHERE word LIKE :word")
    suspend fun update(word: String)
}