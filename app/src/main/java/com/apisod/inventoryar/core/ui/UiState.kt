package com.apisod.inventoryar.core.ui

/*
* WHY THIS EXISTS
    * This controls:
        * loading state
        * success state
        * error state
        * for all screens */
sealed class UiState<out T> {

    data object Idle : UiState<Nothing>()

    data object Loading : UiState<Nothing>()

    data class Success<T>(
        val data: T
    ) : UiState<T>()

    data class Error(
        val message: String
    ) : UiState<Nothing>()
}