package com.foodandservice.di

import com.foodandservice.domain.usecases.auth.GetAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.GetUserAuthTokenUseCase
import com.foodandservice.domain.usecases.auth.IsUserLoggedInUseCase
import com.foodandservice.domain.usecases.auth.ResendSmsUseCase
import com.foodandservice.domain.usecases.auth.SaveAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.auth.SignOutUseCase
import com.foodandservice.domain.usecases.cart.GetCartUseCase
import com.foodandservice.domain.usecases.onboarding.FinishOnboardingUseCase
import com.foodandservice.domain.usecases.onboarding.IsOnboardingFinishedUseCase
import com.foodandservice.domain.usecases.order.GetOrderProductsUseCase
import com.foodandservice.domain.usecases.order.GetOrderStatusUseCase
import com.foodandservice.domain.usecases.restaurant.GetBookingsUseCase
import com.foodandservice.domain.usecases.restaurant.GetFavouriteRestaurantsUseCase
import com.foodandservice.domain.usecases.restaurant.GetOrderHistoryUseCase
import com.foodandservice.domain.usecases.restaurant.GetProductDetailsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantCategoriesUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantDetailsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantProductCategoriesWithProductsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantReviewsUseCase
import com.foodandservice.domain.usecases.restaurant.GetRestaurantsUseCase
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
        GetRestaurantProductCategoriesWithProductsUseCase(customerRepository = get())
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