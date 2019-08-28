package com.josenaves.iddog.common.architecture

sealed class UiState<out T> {
    class Loading<out T>: UiState<T>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Error<out T>(val throwable: Throwable) : UiState<T>()
}