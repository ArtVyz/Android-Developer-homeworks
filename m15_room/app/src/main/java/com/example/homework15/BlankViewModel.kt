package com.example.homework15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BlankViewModel(private val wordDao: WordDao) : ViewModel() {
    val allWords = this.wordDao.getAll()
    val newWord = ""

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