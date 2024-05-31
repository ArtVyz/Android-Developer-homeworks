package com.example.homework11

sealed class State {
    class Loading: State() {
        override fun toString(): String {
            return "Loading"
        }
    }
    class Success: State() {
        override fun toString(): String {
            return "Success"
        }
    }
    class Initial: State() {
        override fun toString(): String {
            return "Initial"
        }
    }
}