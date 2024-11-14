package com.zhadko.priceviewer.ui.screens.authScreen

import androidx.lifecycle.viewModelScope
import com.zhadko.priceviewer.base.BaseViewModel
import com.zhadko.priceviewer.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<AuthState, AuthSingleEvent>(
    initialState = AuthState(
        isLoading = true,
        isLogin = false
    )
) {

    init {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            authRepository.login("r_test@fintatech.com", "kisfiz-vUnvy9-sopnyv")
            launchSingleEvent(AuthSingleEvent.NavigateHome)
        }
    }
}