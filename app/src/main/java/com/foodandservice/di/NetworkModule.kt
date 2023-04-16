package com.foodandservice.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.foodandservice.common.Constants
import com.foodandservice.data.remote.service.AuthService
import com.foodandservice.data.remote.service.CustomerService
import com.foodandservice.data.remote.service.StripeService
import com.foodandservice.network.AcceptLanguageInterceptor
import com.foodandservice.util.AuthInterceptor
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

            addInterceptor(AuthInterceptor(getAuthTokenUseCase = get()))
            addInterceptor(AcceptLanguageInterceptor(context = get()))
            addInterceptor(FlipperOkhttpInterceptor(get(), true))
            addInterceptor(
                ChuckerInterceptor.Builder(get())
                    .collector(ChuckerCollector(get()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single { NetworkFlipperPlugin() }

    single(named("AuthService")) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.FYS_AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("CustomerService")) {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Constants.FYS_CUSTOMER_BASE_URL)
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
        get<Retrofit>(named("AuthService")).create(AuthService::class.java)
    }

    single {
        get<Retrofit>(named("CustomerService")).create(CustomerService::class.java)
    }

    single {
        get<Retrofit>(named("StripeService")).create(StripeService::class.java)
    }
}