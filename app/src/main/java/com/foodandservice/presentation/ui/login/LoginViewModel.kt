package com.foodandservice.presentation.ui.login

import android.content.Context
import android.telephony.TelephonyManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.R
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInFirstPhaseUseCase: SignInFirstPhaseUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(phone: String) {
        viewModelScope.launch {
            when (val response = signInFirstPhaseUseCase(phone)) {
                is Resource.Success -> _loginState.value = LoginState.Success(phone)
                is Resource.Loading -> _loginState.value = LoginState.Loading
                is Resource.Error -> _loginState.value = LoginState.Error(response.message)
            }
        }
    }

    fun getPhonePrefix(context: Context) {
        var countryCode = ""

        val manager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val countryId = manager.simCountryIso.uppercase(Locale.getDefault())
        val countryCodes = context.resources.getStringArray(R.array.CountryCodes)

        for (i in countryCodes.indices) {
            val entry = countryCodes[i].split(",").toTypedArray()
            if (entry[1].trim { it <= ' ' } == countryId.trim { it <= ' ' }) {
                countryCode = entry[0]
                break
            }
        }

        _loginState.value = LoginState.LoadPhonePrefix("+$countryCode")
    }
}