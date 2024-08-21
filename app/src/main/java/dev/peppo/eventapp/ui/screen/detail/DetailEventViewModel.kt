package dev.peppo.eventapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailEventViewModel(
    private val eventRepository: EventsRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailEventResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DetailEventResponse>> get() = _uiState

    fun getDetailEvent(eventId: Int) {
        viewModelScope.launch {
            eventRepository.getDetailEvent(eventId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { detailEvent ->
                    _uiState.value = UiState.Success(detailEvent)
                }
        }
    }
}