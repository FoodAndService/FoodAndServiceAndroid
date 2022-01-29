package com.foodandservice.data.model.response

data class Response(
    val statusCode: Int,
    val data: List<String>,
    val error: String?
)