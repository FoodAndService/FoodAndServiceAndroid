package com.foodandservice.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.onboarding.FinishOnboardingUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel(private val finishOnboardingUseCase: FinishOnboardingUseCase) :
    ViewModel() {

    fun finishOnboarding() {
        viewModelScope.launch {
            finishOnboardingUseCase()
        }
    }
}