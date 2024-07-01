package com.example.homework11

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Initial)
    private val scope = viewModelScope
    private val searchingString = MutableStateFlow("")
    val state = _state.asStateFlow()
    val foundedString = MutableStateFlow("Здесь будет отображаться результат поиска")

    @OptIn(FlowPreview::class)
    fun searchStarted(text: String) {
        searchingString.value = text
        scope.launch {
            searchingString.debounce(300).collect { textResult ->
                if(textResult.length < 3) {
                    _state.value = State.Initial
                } else {
                    _state.value = State.Loading
                    foundedString.value = "Идет поиск"
                    delay(5000)
                    _state.value = State.Success(textResult)
                    foundedString.value = "По запросу \"$text\" ничего не найдено"
                }
            }
        }
    }

    fun onClick() {
        scope.coroutineContext.job.cancelChildren()
        _state.value = State.Initial
        foundedString.value = "Поиск отменен"
    }
}