package com.example.homework15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BlankViewModel(private val wordDao: WordDao) : ViewModel() {
    val newWord = ""
    val allWords = this.wordDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun buttonAdd() {
        viewModelScope.launch {
            wordDao.insert(
                Word (
                    word = newWord,
                    repetitions = 0
                )
            )
        }
    }

}