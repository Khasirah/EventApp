package dev.peppo.eventapp.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.peppo.eventapp.di.Injection
import dev.peppo.eventapp.domain.usecase.EventUseCase
import dev.peppo.eventapp.ui.screen.detail.DetailEventViewModel
import dev.peppo.eventapp.ui.screen.favourite.FavouriteViewModel
import dev.peppo.eventapp.ui.screen.home.HomeViewModel
import kotlin.concurrent.Volatile

class ViewModelFactory private constructor(
    private val eventUseCase: EventUseCase
): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideEventUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(eventUseCase) as T
        }
        if (modelClass.isAssignableFrom(DetailEventViewModel::class.java)) {
            return DetailEventViewModel(eventUseCase) as T
        }
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return FavouriteViewModel(eventUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}