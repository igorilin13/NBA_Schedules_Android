package com.github.igorilin13.feature.settings.impl.settings

import androidx.lifecycle.viewModelScope
import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.feature.settings.impl.settings.state.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : BaseViewModel<SettingsState, NoOpUiEvent>() {

    override fun createDefaultState(): SettingsState {
        return SettingsState(shouldHideScores = null)
    }

    override fun createUiStateFlow(): Flow<SettingsState> {
        return settingsRepository.shouldHideScores().map { hideScores ->
            SettingsState(shouldHideScores = hideScores)
        }
    }

    fun setHideScores(value: Boolean) {
        viewModelScope.launch {
            settingsRepository.setHideScores(value)
        }
    }

}