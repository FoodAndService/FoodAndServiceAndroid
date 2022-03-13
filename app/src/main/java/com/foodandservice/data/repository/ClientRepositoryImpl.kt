package com.foodandservice.data.repository

import com.foodandservice.data.remote.ClientApi
import com.foodandservice.domain.model.RequestPhone
import com.foodandservice.domain.model.RequestPhoneVerify
import com.foodandservice.domain.repository.ClientRepository
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(private val api: ClientApi) : ClientRepository {
    override fun smsSend(authToken: String, phone: String): String {
        return api.smsSend(authToken, RequestPhone(phone))
    }

    override fun smsVerify(authToken: String, phone: String, code: String): String {
        return api.smsVerify(authToken, RequestPhoneVerify(phone, code))
    }
}