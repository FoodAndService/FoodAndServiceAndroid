package com.foodandservice.domain.util

sealed class Resource<T>(val data: T? = null, val message: String = "") {
    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(message: String, data: T? = null) : Resource<T>(data, message)
}