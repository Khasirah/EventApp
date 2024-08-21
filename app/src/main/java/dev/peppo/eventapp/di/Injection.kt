package dev.peppo.eventapp.di

import dev.peppo.eventapp.data.repository.EventsRepository
import dev.peppo.eventapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): EventsRepository {
        return EventsRepository.getInstance(ApiConfig.getApiService())
    }
}