package com.foodandservice.di

import com.foodandservice.data.remote.ClientApi
import com.foodandservice.data.remote.RestaurantApi
import com.foodandservice.data.repository.ClientRepositoryImpl
import com.foodandservice.data.repository.RestaurantRepositoryImpl
import com.foodandservice.domain.repository.ClientRepository
import com.foodandservice.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideClientRepository(api: ClientApi): ClientRepository {
        return ClientRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRestaurantRepository(api: RestaurantApi): RestaurantRepository {
        return RestaurantRepositoryImpl(api)
    }
}