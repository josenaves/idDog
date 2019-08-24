package com.josenaves.iddog.common.architecture

sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    data class Success(val data: String): UiState()
    data class Error(val throwable: Throwable) : UiState()
}