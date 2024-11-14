package com.zhadko.priceviewer.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, SingleEvent>(
    initialState: State,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _singleEvent = MutableSharedFlow<SingleEvent>()
    val singleEvent = _singleEvent.asSharedFlow()

    protected fun updateState(newState: State) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { newState }
        }
    }

    protected fun launchSingleEvent(singleEvent: SingleEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _singleEvent.emit(singleEvent)
        }
    }
}