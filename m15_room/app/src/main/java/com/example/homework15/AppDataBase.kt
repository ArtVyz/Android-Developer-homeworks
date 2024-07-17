package com.example.homework15

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordDao() : WordDao
}