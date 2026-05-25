package com.apisod.inventoryar.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->

            throwable.printStackTrace()
        }
}