package com.example.homework11

sealed class State {
    data object Loading : State() {
        override fun toString(): String {
            return "Loading"
        }
    }

    data object Success : State() {
        override fun toString(): String {
            return "Success"
        }
    }

    data object Initial : State() {
        override fun toString(): String {
            return "Initial"
        }
    }
}