package dev.peppo.core.domain.usecase

import dev.peppo.core.domain.model.DetailEventResDomain
import dev.peppo.core.domain.model.Event
import dev.peppo.core.domain.model.EventResDomain
import kotlinx.coroutines.flow.Flow

interface EventUseCase {

    suspend fun getAllEvent(): Flow<EventResDomain>
    suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResDomain>
    fun getAllFavouriteEvents(): Flow<List<Event>>
    suspend fun saveFavouriteEvent(event: Event)
    suspend fun deleteFavouriteEvent(event: Event)
    fun isFavouriteEvent(id: Int): Flow<Boolean>
}