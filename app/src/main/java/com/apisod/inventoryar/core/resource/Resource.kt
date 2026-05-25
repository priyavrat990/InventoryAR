package com.apisod.inventoryar.core.resource

/*
* WHY THIS EXISTS
    * Every:
        * API response
        * Firebase response
        * Room response
        * TensorFlow result
        * will use this wrapper.*/
sealed class Resource<out T> {

    data class Success<T>(
        val data: T
    ) : Resource<T>()

    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : Resource<Nothing>()

    data object Loading : Resource<Nothing>()
}