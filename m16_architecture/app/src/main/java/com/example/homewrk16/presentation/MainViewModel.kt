package com.example.homewrk16.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homewrk16.domain.GetUsefulActivityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: GetUsefulActivityUseCase) : ViewModel() {
    private val _receivedQuote = MutableStateFlow("")
    val receivedQuote = _receivedQuote.asStateFlow()
    fun reloadUsefulActivity() {
        viewModelScope.launch {
            val data = useCase.execute()
            _receivedQuote.value = data.quote.body
        }
    }
}