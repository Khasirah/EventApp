package dev.peppo.eventapp.data.repository

import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.remote.response.EventResponse
import dev.peppo.eventapp.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class EventsRepository private constructor(
    private val apiService: ApiService
) {
    suspend fun getAllEvent(): Flow<EventResponse> {
        return flowOf(apiService.getAllEvent())
    }

    suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResponse> {
        return flowOf(apiService.getDetailEvent(eventId))
    }

    companion object {
        @Volatile
        private var instance: EventsRepository? = null
        fun getInstance(
            apiService: ApiService
        ): EventsRepository = instance ?: synchronized(this) {
            instance ?: EventsRepository(apiService)
        }.also { instance = it }
    }
}