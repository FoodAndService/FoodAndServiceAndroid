package com.foodandservice.di

import com.foodandservice.common.Constants
import com.foodandservice.data.remote.ClientApi
import com.foodandservice.data.remote.RestaurantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideClientApi(): ClientApi {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE).build()
            chain.proceed(request)
        })
        return Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
            .create(ClientApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantApi(): RestaurantApi {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE).build()
            chain.proceed(request)
        })
        return Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
            .create(RestaurantApi::class.java)
    }
}