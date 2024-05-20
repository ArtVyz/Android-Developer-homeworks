package com.example.homework11

sealed class State {
    object Loading: State()
    object Success: State()
}