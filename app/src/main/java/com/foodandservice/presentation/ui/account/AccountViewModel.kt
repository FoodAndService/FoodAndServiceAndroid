package com.foodandservice.presentation.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.auth.SignOutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountViewModel(private val signOutUseCase: SignOutUseCase) : ViewModel() {
    private val _accountState = MutableStateFlow<AccountState>(AccountState.Idle)
    val accountState: StateFlow<AccountState> = _accountState.asStateFlow()

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
            _accountState.emit(AccountState.SignOut)
        }
    }
}