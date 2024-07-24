package com.example.homework15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BlankViewModel(private val wordDao: WordDao) : ViewModel() {
    private val _state = MutableStateFlow(State.INITIAL)
    val state = _state.asStateFlow()
    val newWord = MutableStateFlow("")
    val allWords = this.wordDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun buttonAdd() {
        viewModelScope.launch {
            newWord.collect{
                _state.value = State.LOADING
                if (insertWord(it)) {
                    try {
                        wordDao.insert(
                            Word(
                                word = it,
                                repetitions = 0
                            )
                        )
                        _state.value = State.SUCCESS
                    } catch (t: Throwable) {
                        wordDao.update(
                            it
                        )
                        _state.value = State.SUCCESS
                    }
                    currentCoroutineContext().cancel()
                } else {
                    _state.value = State.ERROR
                    currentCoroutineContext().cancel()
                }
            }
        }
    }

    fun buttonDelete() {
        viewModelScope.launch {
            _state.value = State.LOADING
            allWords.value.let {
                val iterator = it.iterator()
                while(iterator.hasNext()) {
                    val item = iterator.next()
                    wordDao.delete(item)
                }
            }
            _state.value = State.SUCCESS
        }
    }

    private fun insertWord (word: String) : Boolean {
        val regex = Regex("[0-9 .,]+")
        val regexOrNot = regex.containsMatchIn(word)
        return !(word.isEmpty() || regexOrNot)
    }
}