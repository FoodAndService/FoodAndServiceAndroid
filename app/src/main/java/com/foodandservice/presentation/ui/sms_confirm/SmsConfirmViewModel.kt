package com.foodandservice.presentation.ui.sms_confirm

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val saveUserTokenUseCase: SaveUserTokenUseCase
) : ViewModel() {

    private val _smsConfirmState = MutableStateFlow<SmsConfirmState>(SmsConfirmState.Idle)
    val smsConfirmState: StateFlow<SmsConfirmState> = _smsConfirmState.asStateFlow()

    private var _countDownTimerState = MutableStateFlow(CountDownTimerState())
    val countDownTimerState = _countDownTimerState.asStateFlow()

    private var timer: CountDownTimer? = null

    init {
        initCountDownTimer()
    }

    fun sendSms(phone: String, smsCode: String) {
        viewModelScope.launch {
            when (val response = signInSecondPhaseUseCase(phone, smsCode)) {
                is Resource.Success -> {
                    response.data?.let { phaseWithAuth ->
                        saveUserTokenUseCase(phaseWithAuth.authUser)

                        when (phaseWithAuth.currentPhase) {
                            "phone_verified" -> _smsConfirmState.value =
                                SmsConfirmState.SuccessNewCustomer(phaseWithAuth.authUser)
                            "info_added" -> _smsConfirmState.value =
                                SmsConfirmState.SuccessExistentCustomer
                        }
                    }
                }
                is Resource.Loading -> _smsConfirmState.value = SmsConfirmState.Loading
                is Resource.Error -> _smsConfirmState.value =
                    SmsConfirmState.Error(response.message)
            }
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
    val isBtnEnabled: Boolean = false,
    val time: Long = 59
)