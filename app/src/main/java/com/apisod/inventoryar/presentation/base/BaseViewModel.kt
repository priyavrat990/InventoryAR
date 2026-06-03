package com.apisod.inventoryar.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel :
    ViewModel() {

    private val _errorFlow =
        MutableSharedFlow<String>()

    val errorFlow =
        _errorFlow.asSharedFlow()

    protected suspend fun emitError(
        message: String
    ) {

        _errorFlow.emit(
            message
        )
    }
}