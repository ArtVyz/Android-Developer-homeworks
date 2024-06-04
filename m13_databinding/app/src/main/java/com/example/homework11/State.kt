package com.example.homework11

sealed class State {
    data object Loading : State()

    data object Success : State()

    data object Initial : State()
}