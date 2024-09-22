package dev.peppo.eventapp.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.peppo.eventapp.data.remote.response.EventResponse
import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val eventRepository: EventsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<EventResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<EventResponse>> get() = _uiState

    fun getAllEvent() {
        viewModelScope.launch {
            eventRepository.getAllEvent()
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