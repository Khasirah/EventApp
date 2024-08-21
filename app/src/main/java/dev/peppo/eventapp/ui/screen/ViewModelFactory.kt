package dev.peppo.eventapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.ui.screen.detail.DetailEventViewModel
import dev.peppo.eventapp.ui.screen.home.HomeViewModel

class ViewModelFactory (
    private val eventsRepository: EventsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(eventsRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailEventViewModel::class.java)) {
            return DetailEventViewModel(eventsRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}