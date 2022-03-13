package com.foodandservice.domain.repository

interface ClientRepository {
    fun smsSend(authToken: String, phone: String): String
    fun smsVerify(authToken: String, phone: String, code: String): String
}