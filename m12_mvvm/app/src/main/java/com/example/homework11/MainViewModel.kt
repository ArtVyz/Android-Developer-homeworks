package com.example.homework11

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val authService: IAuthService): ViewModel() {
    private val _state = MutableStateFlow<State>(State.Success)
    private var search = ""
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val viewState = MutableStateFlow(
        ViewState(
            isButtonEnabled = false
        )
    )

    val state = _state.asStateFlow()
    val viewStateFlow: Flow<ViewState> = viewState

    private val found = MutableSharedFlow<Unit>()
    val foundFlow: Flow<Unit> = found

    fun searchQueryEntered(searchQuery: String) {
        search = searchQuery
        viewState.value = viewState.value.copy(isButtonEnabled = checkInputValid())
    }

    private fun checkInputValid(): Boolean = search.length >= 3

    fun searchStarted() {
            _state.value = State.Loading
            authService.auth(search) {
                _state.value = State.Success
                scope.launch {
                    found.emit(Unit)
                }
            }
        }

    data class ViewState(
        val isButtonEnabled: Boolean
    )
}