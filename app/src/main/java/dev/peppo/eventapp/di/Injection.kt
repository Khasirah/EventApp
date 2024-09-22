package dev.peppo.eventapp.di

import android.content.Context
import dev.peppo.eventapp.data.local.room.EventRoomDatabase
import dev.peppo.eventapp.data.remote.retrofit.ApiConfig
import dev.peppo.eventapp.data.repository.EventsRepository

object Injection {
    fun provideRepository(context: Context): EventsRepository {
        val apiService = ApiConfig.getApiService()
        val eventDatabase = EventRoomDatabase.getDatabase(context)
        val eventDao = eventDatabase.eventDao()
        return EventsRepository.getInstance(apiService, eventDao)
    }
}