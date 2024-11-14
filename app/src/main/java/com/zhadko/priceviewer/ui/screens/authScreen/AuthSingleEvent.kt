package com.zhadko.priceviewer.ui.screens.authScreen

sealed class AuthSingleEvent {

    data object NavigateHome : AuthSingleEvent()
}