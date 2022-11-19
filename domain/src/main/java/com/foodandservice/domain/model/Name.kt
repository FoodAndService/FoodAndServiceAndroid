package com.foodandservice.domain.model

data class Name(val name: String)

class InvalidNameFormatException : Exception()