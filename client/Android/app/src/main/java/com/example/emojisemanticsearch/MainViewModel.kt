package com.example.emojisemanticsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emojisemanticsearch.entity.EmojiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private var _uiState = MutableStateFlow<UiState>(UiState.Default)
    val uiState get() = _uiState

    fun fetchSuccessAfter2s() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            withContext(Dispatchers.IO) {
                delay(2000)
                _uiState.emit(UiState.Success(testData()))
            }
        }
    }

    private fun testData() = listOf(
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear"),
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear"),
        EmojiEntity("🐶", "Dog"),
        EmojiEntity("🐱", "Cat"),
        EmojiEntity("🐭", "Mouse"),
        EmojiEntity("🐹", "Hamster"),
        EmojiEntity("🐰", "Rabbit"),
        EmojiEntity("🦊", "Fox"),
        EmojiEntity("🐻", "Bear"),
        EmojiEntity("🐼", "Panda"),
        EmojiEntity("🐻‍❄️", "Polar Bear")
    )
}

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: List<EmojiEntity>): UiState()
    data class Error(val message: String): UiState()
    object Default: UiState()
}