package dev.peppo.eventapp.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.peppo.core.data.remote.response.EventResponse
import dev.peppo.core.domain.model.EventResDomain
import dev.peppo.core.domain.usecase.EventUseCase
import dev.peppo.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val eventUseCase: EventUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<EventResDomain>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<EventResDomain>> get() = _uiState

    fun getAllEvent() {
        viewModelScope.launch {
            eventUseCase.getAllEvent()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                    Log.d("HomeViewModel: ", it.message.toString())
                }
                .collect { eventResponse ->
                    _uiState.value = UiState.Success(eventResponse)
                }
        }
    }
}