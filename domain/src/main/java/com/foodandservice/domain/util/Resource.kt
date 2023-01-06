package com.foodandservice.domain.util

sealed class Resource<T>(val data: T? = null, val exception: Exception? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(exception: Exception) : Resource<T>(exception = exception)
}