package com.example.homework13.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework13.RetrofitInstance
import com.example.homework13.State
import com.example.homework13.UsersInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(State.INITIAL)
    val state = _state.asStateFlow()
    private val scope = viewModelScope
    val searchingString = MutableStateFlow("")

    fun getUserModel() {
        scope.launch {
            _state.value = State.LOADING
            val data = RetrofitInstance.searchUserApi.getUserInfo()
            searchingString.value = data.toString()
            _state.value = State.SUCCESS
        }
    }
}