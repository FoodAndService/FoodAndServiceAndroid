package com.foodandservice.domain.util

sealed class ApiResponse<T>(val data: T? = null, val exception: Exception? = null) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Failure<T>(exception: Exception) : ApiResponse<T>(exception = exception)
}