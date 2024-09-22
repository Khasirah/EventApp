package dev.peppo.eventapp.di

import android.content.Context
import dev.peppo.eventapp.data.local.room.EventRoomDatabase
import dev.peppo.eventapp.data.remote.retrofit.ApiConfig
import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.domain.repository.IEventRepository
import dev.peppo.eventapp.domain.usecase.EventInteractor
import dev.peppo.eventapp.domain.usecase.EventUseCase

object Injection {
    private fun provideRepository(context: Context): IEventRepository {
        val apiService = ApiConfig.getApiService()
        val eventDatabase = EventRoomDatabase.getDatabase(context)
        val eventDao = eventDatabase.eventDao()
        return EventsRepository.getInstance(apiService, eventDao)
    }

    fun provideEventUseCase(context: Context): EventUseCase {
        val repository = provideRepository(context)
        return EventInteractor(repository)
    }
}