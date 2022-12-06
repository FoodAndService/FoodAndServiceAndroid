package com.foodandservice.di

import com.foodandservice.data.repository.CustomerRepositoryImpl
import com.foodandservice.data.repository.RestaurantRepositoryImpl
import com.foodandservice.data.repository.UserPreferencesRepositoryImpl
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.RestaurantRepository
import com.foodandservice.domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl(customerService = get())
    }

    single<RestaurantRepository> {
        RestaurantRepositoryImpl(restarauntService = get())
    }

    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(dataStore = get())
    }
}