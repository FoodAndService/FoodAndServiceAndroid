package com.foodandservice.data.network

import com.foodandservice.data.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Request

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE).build()
            chain.proceed(request)
        })

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.API_BASE_URL)
            .client(httpClient.build()).build()
    }

    @Singleton
    @Provides
    fun provideClientRepository(retrofit: Retrofit): RestaurantClientService {
        return retrofit.create(RestaurantClientService::class.java)
    }

    @Singleton
    @Provides
    fun provideRestaurantRepository(retrofit: Retrofit): RestaurantService {
        return retrofit.create(RestaurantService::class.java)
    }
}