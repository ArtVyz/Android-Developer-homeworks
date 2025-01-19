package com.example.homework17.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17.data.api.RetrofitInstance
import com.example.homework17.entity.Photos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val currentRequest = MutableStateFlow(emptyList<Photos>())
    private val scope = viewModelScope

    fun getUserModel() {
        scope.launch {
            val data = RetrofitInstance.searchUserApi.getUserInfo()
            currentRequest.value = data.photos
        }
    }
}