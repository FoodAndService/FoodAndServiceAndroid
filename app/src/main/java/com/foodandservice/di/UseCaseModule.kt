package com.foodandservice.di

import com.foodandservice.domain.usecases.auth.*
import com.foodandservice.domain.usecases.cart.GetCartUseCase
import com.foodandservice.domain.usecases.onboarding.FinishOnboardingUseCase
import com.foodandservice.domain.usecases.onboarding.IsOnboardingFinishedUseCase
import com.foodandservice.domain.usecases.order.GetOrderProductsUseCase
import com.foodandservice.domain.usecases.order.GetOrderStatusUseCase
import com.foodandservice.domain.usecases.restaurant.*
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.usecases.sign.SignInSecondPhaseUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetAuthCurrentPhaseUseCase(userPreferencesRepository = get())
    }

    single {
        GetFavouriteRestaurantsUseCase(customerRepository = get())
    }

    single {
        //GetCategoryRestaurantsUseCase(customerRepository = get())
    }

    single {
        GetOrderProductsUseCase(customerRepository = get())
    }

    single {
        GetProductDetailsUseCase(customerRepository = get())
    }

    single {
        GetRestaurantDetailsUseCase(customerRepository = get())
    }

    single {
        GetRestaurantDetailsExtraUseCase(customerRepository = get())
    }

    single {
        GetRestaurantReviewsUseCase(customerRepository = get())
    }

    single {
        GetOrderStatusUseCase(customerRepository = get())
    }

    single {
        GetBookingsUseCase(customerRepository = get())
    }

    single {
        GetCartUseCase(customerRepository = get())
    }

    single {
        GetOrderHistoryUseCase(customerRepository = get())
    }

    single {
        GetUserAuthTokenUseCase(userPreferencesRepository = get())
    }

    single {
        GetRestaurantsUseCase(customerRepository = get())
    }

    single {
        GetRestaurantCategoriesUseCase(customerRepository = get())
    }

    single {
        IsUserLoggedInUseCase(userPreferencesRepository = get())
    }

    single {
        SaveAuthCurrentPhaseUseCase(userPreferencesRepository = get())
    }

    single {
        ResendSmsUseCase(authRepository = get())
    }

    single {
        SignOutUseCase(preferencesRepository = get())
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
        SignInFirstPhaseUseCase(authRepository = get())
    }

    single {
        SignInSecondPhaseUseCase(authRepository = get())
    }

    single {
        SignUpFirstPhaseUseCase(authRepository = get())
    }
}