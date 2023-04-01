package com.foodandservice.util

import com.foodandservice.domain.usecases.auth.GetUserAuthTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val getAuthTokenUseCase: GetUserAuthTokenUseCase) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = runBlocking { getAuthTokenUseCase() }

        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}