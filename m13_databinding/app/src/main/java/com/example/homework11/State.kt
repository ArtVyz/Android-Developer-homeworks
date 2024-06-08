package com.example.homework11

sealed class State (val text: String) {

    data object Loading : State("Идет поиск")
    class Success (textResult: String) : State( "по запросу \"$textResult\" ничего не найдено ")

    data object Initial : State("Здесь будет отображаться результат запроса")

}