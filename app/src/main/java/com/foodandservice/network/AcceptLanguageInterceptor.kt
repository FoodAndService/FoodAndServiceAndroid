package com.foodandservice.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AcceptLanguageInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val locale = context.resources.configuration.locales[0]
        val languageTag = locale.toLanguageTag()
        val modifiedRequest =
            originalRequest.newBuilder().header("Accept-Language", languageTag).build()
        return chain.proceed(modifiedRequest)
    }
}