package dev.peppo.eventapp.domain.usecase

import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.remote.response.EventResponse
import dev.peppo.eventapp.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventUseCase {

    suspend fun getAllEvent(): Flow<EventResponse>
    suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResponse>
    fun getAllFavouriteEvents(): Flow<List<Event>>
    suspend fun saveFavouriteEvent(event: Event)
    suspend fun deleteFavouriteEvent(event: Event)
    fun isFavouriteEvent(id: Int): Flow<Boolean>
}