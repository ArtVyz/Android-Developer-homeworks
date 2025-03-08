package com.example.homework18

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.homework18.entity.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll(): Flow<List<Photo>>

    @Insert
    suspend fun insert(photo: Photo)
}