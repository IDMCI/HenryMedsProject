package com.example.duncanclark.henrymedsproject.ui.state

/**
 * Represents different states of UI.
 */
sealed class UiState<out T: Any> {
    data object Idle: UiState<Nothing>()
    data object Loading: UiState<Nothing>()
    data class Success<T: Any>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}