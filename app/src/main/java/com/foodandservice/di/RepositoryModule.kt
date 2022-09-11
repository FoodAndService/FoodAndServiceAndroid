package com.foodandservice.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.foodandservice.data.remote.api.CustomerService
import com.foodandservice.data.remote.api.RestarauntService
import com.foodandservice.data.repository.CustomerRepositoryImpl
import com.foodandservice.data.repository.RestaurantRepositoryImpl
import com.foodandservice.data.repository.UserPreferencesRepositoryImpl
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.repository.UserPreferencesRepository
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
    fun provideCustomerRepository(api: CustomerService): CustomerRepository {
        return CustomerRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRestaurantRepository(api: RestarauntService): RestaurantRepository {
        return RestaurantRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(dataStore: DataStore<Preferences>): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(dataStore)
    }
}