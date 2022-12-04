package com.foodandservice.di

import com.foodandservice.domain.usecases.auth.*
import com.foodandservice.domain.usecases.onboarding.FinishOnboardingUseCase
import com.foodandservice.domain.usecases.onboarding.IsOnboardingFinishedUseCase
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.usecases.sign.SignInSecondPhaseUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCurrentPhaseUseCase(userPreferencesRepository = get())
    }

    single {
        GetUserTokenUseCase(userPreferencesRepository = get())
    }

    single {
        IsUserLoggedInUseCase(userPreferencesRepository = get())
    }

    single {
        SaveCurrentPhaseUseCase(userPreferencesRepository = get())
    }

    single {
        SaveUserTokenUseCase(userPreferencesRepository = get())
    }

    single {
        FinishOnboardingUseCase(userPreferencesRepository = get())
    }

    single {
        IsOnboardingFinishedUseCase(userPreferencesRepository = get())
    }

    single {
        SignInFirstPhaseUseCase(customerRepository = get())
    }

    single {
        SignInSecondPhaseUseCase(customerRepository = get())
    }

    single {
        SignUpFirstPhaseUseCase(customerRepository = get())
    }
}