package com.github.igorilin13.feature.settings.impl.settings

import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.feature.settings.impl.settings.state.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : BaseViewModel<SettingsState, NoOpUiEvent>() {

    override fun createDefaultState(): SettingsState {
        return SettingsState()
    }

    override fun createUiStateFlow(): Flow<SettingsState> {
        return flowOf(SettingsState())
    }

}