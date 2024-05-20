package com.example.homework11

interface IAuthService {
     fun auth(searchQuery: String, onSearchedIn: () -> Unit)
}