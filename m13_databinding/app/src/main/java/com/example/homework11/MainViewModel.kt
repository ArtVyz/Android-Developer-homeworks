package com.example.homework11

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Initial())
    var search = ""
    private val scope = viewModelScope
    private val viewState = MutableStateFlow(
        ViewState(
            isButtonEnabled = false
        )
    )

    val state = _state.asStateFlow()
    val viewStateFlow: Flow<ViewState> = viewState


    fun searchQueryEntered(searchQuery: String) {
        search = searchQuery
        viewState.value = viewState.value.copy(isButtonEnabled = checkInputValid())
    }

    private fun checkInputValid(): Boolean = search.length >= 3

    fun searchStarted(query: String) {
        scope.launch {
            _state.value = State.Loading()
            delay(6000)
            search = query
            _state.value = State.Success()

        }
    }

    data class ViewState(
        val isButtonEnabled: Boolean
    )
}