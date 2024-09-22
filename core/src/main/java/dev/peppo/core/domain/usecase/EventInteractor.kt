package dev.peppo.core.domain.usecase

import dev.peppo.core.data.remote.response.DetailEventResponse
import dev.peppo.core.data.remote.response.EventResponse
import dev.peppo.core.domain.model.Event
import dev.peppo.core.domain.repository.IEventRepository
import kotlinx.coroutines.flow.Flow

class EventInteractor(private val eventRepository: IEventRepository): EventUseCase {
    override suspend fun getAllEvent(): Flow<EventResponse> = eventRepository.getAllEvent()

    override suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResponse> = eventRepository.getDetailEvent(eventId)

    override fun getAllFavouriteEvents(): Flow<List<Event>> = eventRepository.getAllFavouriteEvents()

    override suspend fun saveFavouriteEvent(event: Event) = eventRepository.saveFavouriteEvent(event)

    override suspend fun deleteFavouriteEvent(event: Event) = eventRepository.deleteFavouriteEvent(event)

    override fun isFavouriteEvent(id: Int): Flow<Boolean> = eventRepository.isFavouriteEvent(id)
}