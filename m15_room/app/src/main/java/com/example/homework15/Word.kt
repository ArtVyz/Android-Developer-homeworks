package com.example.homework15

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey
    @ColumnInfo("word")
    val word: String,
    @ColumnInfo("repetitions")
    val repetitions: Int
)