package com.foodandservice.presentation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.onboarding.FinishOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val finishOnboardingUseCase: FinishOnboardingUseCase) :
    ViewModel() {

    fun finishOnboarding() {
        viewModelScope.launch {
            finishOnboardingUseCase()
        }
    }
}