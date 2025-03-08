package com.example.homework18.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey
    @ColumnInfo (name = "path")
    val path: String,
    @ColumnInfo (name = "data")
    val data: String
)