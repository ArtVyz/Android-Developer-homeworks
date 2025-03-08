package com.example.homework18.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.PhotoDao
import com.example.homework18.entity.Photo
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainViewModel(private val dao: PhotoDao) : ViewModel() {

    val photos = this.dao.getAll()
    fun daoInsert(path: String, data: String) {
        viewModelScope.launch {
            dao.insert(Photo(path,data))
        }
    }
}