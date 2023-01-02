package com.foodandservice.di

import com.foodandservice.presentation.ui.home.HomeViewModel
import com.foodandservice.presentation.ui.home_category_filter.HomeCategoryFilterViewModel
import com.foodandservice.presentation.ui.login.LoginViewModel
import com.foodandservice.presentation.ui.onboarding.OnboardingViewModel
import com.foodandservice.presentation.ui.restaurant_booking.RestaurantBookingViewModel
import com.foodandservice.presentation.ui.restaurant_details.RestaurantDetailsViewModel
import com.foodandservice.presentation.ui.review_create.ReviewCreateViewModel
import com.foodandservice.presentation.ui.signup_finish.SignUpFinishViewModel
import com.foodandservice.presentation.ui.sms_confirm_auth.SmsConfirmViewModel
import com.foodandservice.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel()
    }

    viewModel {
        HomeCategoryFilterViewModel()
    }

    viewModel {
        LoginViewModel(signInFirstPhaseUseCase = get())
    }

    viewModel {
        OnboardingViewModel(finishOnboardingUseCase = get())
    }

    viewModel {
        RestaurantDetailsViewModel()
    }

    viewModel {
        ReviewCreateViewModel()
    }

    viewModel {
        SignUpFinishViewModel(
            signUpFirstPhaseUseCase = get(),
            getCustomerTokenUseCase = get(),
            saveUserTokenUseCase = get(),
            saveAuthCurrentPhaseUseCase = get()
        )
    }

    viewModel {
        SmsConfirmViewModel(
            signInSecondPhaseUseCase = get(),
            saveUserTokenUseCase = get(),
            saveAuthCurrentPhaseUseCase = get()
        )
    }

    viewModel {
        SplashViewModel(isUserLoggedInUseCase = get(), isOnboardingFinishedUseCase = get())
    }

    viewModel {
        RestaurantBookingViewModel()
    }
}