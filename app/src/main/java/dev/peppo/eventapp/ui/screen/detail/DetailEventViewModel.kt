package dev.peppo.eventapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.peppo.core.data.remote.response.DetailEventResponse
import dev.peppo.core.domain.model.Event
import dev.peppo.core.domain.usecase.EventUseCase
import dev.peppo.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailEventViewModel(
    private val eventUseCase: EventUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailEventResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DetailEventResponse>> get() = _uiState
    private val _isFavouriteEvent: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
    val isFavouriteEvent: StateFlow<UiState<Boolean>> get() = _isFavouriteEvent

    fun getDetailEvent(eventId: Int) {
        viewModelScope.launch {
            eventUseCase.getDetailEvent(eventId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { detailEvent ->
                    _uiState.value = UiState.Success(detailEvent)
                }
        }
    }

    fun isFavouriteEvent(id: Int) {
        viewModelScope.launch {
            eventUseCase.isFavouriteEvent(id)
                .catch {
                    _isFavouriteEvent.value = UiState.Error(it.message.toString())
                }
                .collect { isFav ->
                    _isFavouriteEvent.value = UiState.Success(isFav)
                }
        }
    }

    fun updateIsFavouriteEvent(event: Event, isFavouriteEvent: Boolean) {
        viewModelScope.launch {
            if (isFavouriteEvent) {
                eventUseCase.deleteFavouriteEvent(event)
            } else {
                eventUseCase.saveFavouriteEvent(event)
            }
        }
    }
}