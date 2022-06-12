package com.foodandservice.domain.repository

interface ClientRepository {
    fun receiveSmsCode(authToken: String, phone: String): String
    fun verifySmsCode(authToken: String, phone: String, code: String): String
}