package com.foodandservice.presentation.ui.sms_confirm_auth

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.sign.AuthCurrentPhase
import com.foodandservice.domain.usecases.auth.SaveAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.sign.SignInSecondPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SmsConfirmViewModel(
    private val signInSecondPhaseUseCase: SignInSecondPhaseUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveAuthCurrentPhaseUseCase: SaveAuthCurrentPhaseUseCase
) : ViewModel() {

    private val _smsConfirmAuthState =
        MutableStateFlow<SmsConfirmAuthState>(SmsConfirmAuthState.Idle)
    val smsConfirmAuthState: StateFlow<SmsConfirmAuthState> = _smsConfirmAuthState.asStateFlow()

    private var _countDownTimerState = MutableStateFlow(CountDownTimerState())
    val countDownTimerState = _countDownTimerState.asStateFlow()

    private var timer: CountDownTimer? = null

    init {
        initCountDownTimer()
    }

    fun sendSms(phone: String, smsCode: String) {
        viewModelScope.launch {
            _smsConfirmAuthState.value = SmsConfirmAuthState.Loading

            when (val response = signInSecondPhaseUseCase(phone, smsCode)) {
                is Resource.Success -> {
                    response.data?.let { authPhaseWithToken ->
                        saveUserTokenUseCase(authPhaseWithToken.authUser)
                        saveAuthCurrentPhaseUseCase(authPhaseWithToken.currentPhase.name.lowercase())

                        when (authPhaseWithToken.currentPhase) {
                            AuthCurrentPhase.PHONE_VERIFIED -> _smsConfirmAuthState.value =
                                SmsConfirmAuthState.SuccessNewCustomer(authPhaseWithToken.authUser)
                            AuthCurrentPhase.INFO_ADDED -> _smsConfirmAuthState.value =
                                SmsConfirmAuthState.SuccessExistentCustomer
                            AuthCurrentPhase.UNKNOWN -> _smsConfirmAuthState.value =
                                SmsConfirmAuthState.Error("")
                        }
                    }
                }
                is Resource.Failure -> _smsConfirmAuthState.value =
                    SmsConfirmAuthState.Error(response.message)
            }

            _smsConfirmAuthState.value = SmsConfirmAuthState.Idle
        }
    }

    fun initCountDownTimer() {
        _countDownTimerState.update { it.copy(isBtnEnabled = false) }

        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                try {
                    _countDownTimerState.update { it.copy(time = (millisUntilFinished / 1000)) }
                } catch (_: Exception) {
                }
            }

            override fun onFinish() {
                try {
                    _countDownTimerState.update { it.copy(isBtnEnabled = true) }
                    timer?.cancel()
                } catch (_: Exception) {
                }
            }
        }

        timer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}

data class CountDownTimerState(
    val isBtnEnabled: Boolean = false, val time: Long = 59
)