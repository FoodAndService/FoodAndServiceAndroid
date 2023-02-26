package com.foodandservice.di

import com.foodandservice.common.Constants
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.data.remote.service.RestarauntService
import com.foodandservice.data.remote.service.StripeService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE).build()
                )
            })

            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.FYS_AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("StripeService")) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.FYS_STRIPE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(CustomerService::class.java)
    }

    single {
        get<Retrofit>().create(RestarauntService::class.java)
    }

    single {
        get<Retrofit>(named("StripeService")).create(StripeService::class.java)
    }
}