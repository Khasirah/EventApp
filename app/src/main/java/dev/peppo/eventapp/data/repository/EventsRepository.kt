package dev.peppo.eventapp.data.repository

import dev.peppo.eventapp.data.local.entity.Event
import dev.peppo.eventapp.data.local.room.EventDao
import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.remote.response.EventResponse
import dev.peppo.eventapp.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.security.PrivateKey

class EventsRepository private constructor(
    private val apiService: ApiService,
    private val eventDao: EventDao
) {
    suspend fun getAllEvent(): Flow<EventResponse> {
        return flowOf(apiService.getAllEvent())
    }

    suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResponse> {
        return flowOf(apiService.getDetailEvent(eventId))
    }

    fun getAllFavouriteEvents(): Flow<List<Event>> {
        return eventDao.getAllEvent()
    }

    suspend fun saveFavouriteEvent(event: Event) {
        eventDao.insert(event)
    }

    suspend fun deleteFavouriteEvent(event: Event) {
        eventDao.delete(event)
    }

    fun isFavouriteEvent(id: Int): Flow<Boolean> {
        return eventDao.isFavouriteEvent(id)
    }

    companion object {
        @Volatile
        private var instance: EventsRepository? = null
        fun getInstance(
            apiService: ApiService,
            eventDao: EventDao
        ): EventsRepository = instance ?: synchronized(this) {
            instance ?: EventsRepository(apiService, eventDao)
        }.also { instance = it }
    }
}