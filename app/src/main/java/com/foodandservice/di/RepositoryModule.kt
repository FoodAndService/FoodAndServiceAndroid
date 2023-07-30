package com.foodandservice.di

import com.foodandservice.data.remote.datasource.CustomerRemoteDataSource
import com.foodandservice.data.remote.datasource.CustomerRemoteDataSourceImpl
import com.foodandservice.data.repository.AuthRepositoryImpl
import com.foodandservice.data.repository.CustomerRepositoryImpl
import com.foodandservice.data.repository.UserPreferencesRepositoryImpl
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.repository.UserPreferencesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(authService = get())
    }

    single<CustomerRepository> {
        CustomerRepositoryImpl(
            customerRemoteDataSource = get(),
            customerService = get(),
            cartDao = get()
        )
    }

    single<CustomerRemoteDataSource> {
        CustomerRemoteDataSourceImpl(customerService = get())
    }

    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(dataStore = get())
    }
}