package com.foodandservice.presentation.ui.sms_confirm_auth

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.auth.AuthCurrentPhase
import com.foodandservice.domain.usecases.auth.ResendSmsUseCase
import com.foodandservice.domain.usecases.auth.SaveAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.sign.SignInSecondPhaseUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SmsConfirmViewModel(
    private val signInSecondPhaseUseCase: SignInSecondPhaseUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveAuthCurrentPhaseUseCase: SaveAuthCurrentPhaseUseCase,
    private val resendSmsUseCase: ResendSmsUseCase
) : ViewModel() {

    private val _smsConfirmAuthState = MutableSharedFlow<SmsConfirmAuthState>(replay = 10)
    val smsConfirmAuthState: SharedFlow<SmsConfirmAuthState> = _smsConfirmAuthState.asSharedFlow()

    private var _countDownTimerState = MutableStateFlow(CountDownTimerState())
    val countDownTimerState = _countDownTimerState.asStateFlow()

    private var timer: CountDownTimer? = null

    init {
        initCountDownTimer()
    }

    fun sendSms(phone: String, smsCode: String) {
        viewModelScope.launch {
            _smsConfirmAuthState.emit(SmsConfirmAuthState.Loading)

            when (val response = signInSecondPhaseUseCase(phone, smsCode)) {
                is ApiResponse.Success -> {
                    response.data?.let { authPhaseWithToken ->
                        saveUserTokenUseCase(authPhaseWithToken.token)
                        saveAuthCurrentPhaseUseCase(authPhaseWithToken.currentPhase.name.lowercase())

                        when (authPhaseWithToken.currentPhase) {
                            AuthCurrentPhase.PHONE_VERIFIED -> {
                                _smsConfirmAuthState.emit(
                                    SmsConfirmAuthState.SuccessNewCustomer(
                                        authPhaseWithToken.token
                                    )
                                )
                            }

                            AuthCurrentPhase.INFO_ADDED -> {
                                _smsConfirmAuthState.emit(SmsConfirmAuthState.SuccessExistentCustomer)
                            }

                            AuthCurrentPhase.UNKNOWN -> {
                                _smsConfirmAuthState.emit(SmsConfirmAuthState.Error("Something went wrong"))
                            }
                        }
                    }
                }

                is ApiResponse.Failure -> {
                    _smsConfirmAuthState.emit(
                        SmsConfirmAuthState.Error(
                            response.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _smsConfirmAuthState.emit(SmsConfirmAuthState.Idle)
        }
    }

    fun resendSms(phone: String) {
        viewModelScope.launch {
            resendSmsUseCase(customerPhone = CustomerPhone(phone = phone))
            initCountDownTimer()
        }
    }

    fun initCountDownTimer() {
        _countDownTimerState.update { it.copy(isBtnEnabled = false) }

        timer = object : CountDownTimer(10000, 1000) {
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