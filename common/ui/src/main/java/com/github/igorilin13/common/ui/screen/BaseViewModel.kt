package com.github.igorilin13.common.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<S, E> : ViewModel() {
    protected val _uiEvents = MutableSharedFlow<E>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val uiEvents = _uiEvents.asSharedFlow()

    val uiState: StateFlow<S> by lazy {
        createUiStateFlow().stateIn(viewModelScope, SharingStarted.Eagerly, createDefaultState())
    }

    protected abstract fun createDefaultState(): S
    protected abstract fun createUiStateFlow(): Flow<S>
}