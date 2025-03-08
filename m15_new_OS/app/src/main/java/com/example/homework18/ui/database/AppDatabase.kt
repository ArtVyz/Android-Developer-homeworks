package com.example.homework18.ui.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework18.PhotoDao
import com.example.homework18.entity.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao() : PhotoDao
}