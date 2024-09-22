package dev.peppo.eventapp.ui.screen.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.peppo.eventapp.data.local.entity.Event
import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavouriteViewModel (
    private val eventsRepository: EventsRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Event>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Event>>> get() = _uiState

    fun getAllFavouriteEvent() {
        viewModelScope.launch {
            eventsRepository.getAllFavouriteEvents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                    Log.d("FavouriteViewModel: ", it.message.toString())
                }
                .collect { favEvents ->
                    _uiState.value = UiState.Success(favEvents)
                }
        }
    }
}