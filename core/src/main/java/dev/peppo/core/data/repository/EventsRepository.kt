package dev.peppo.core.data.repository

import dev.peppo.core.data.local.room.EventDao
import dev.peppo.core.data.remote.response.DetailEventResponse
import dev.peppo.core.data.remote.retrofit.ApiService
import dev.peppo.core.domain.model.DetailEventResDomain
import dev.peppo.core.domain.model.EventResDomain
import dev.peppo.core.domain.repository.IEventRepository
import dev.peppo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import dev.peppo.core.domain.model.Event as EventDomain

class EventsRepository(
    private val apiService: ApiService,
    private val eventDao: EventDao
): IEventRepository {
    override suspend fun getAllEvent(): Flow<EventResDomain> {
        val response = apiService.getAllEvent()
        return flowOf(DataMapper.mapEventResToEntities(response))
    }

    override suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResDomain> {
        val response = apiService.getDetailEvent(eventId)
        return flowOf(DataMapper.mapDetailEventResToEntities(response))
    }

    override fun getAllFavouriteEvents(): Flow<List<EventDomain>> {
        return eventDao.getAllEvent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun saveFavouriteEvent(event: EventDomain) {
        eventDao.insert(
            DataMapper.mapDomainToEntities(event)
        )
    }

    override suspend fun deleteFavouriteEvent(event: EventDomain) {
        eventDao.delete(
            DataMapper.mapDomainToEntities(event)
        )
    }

    override fun isFavouriteEvent(id: Int): Flow<Boolean> {
        return eventDao.isFavouriteEvent(id)
    }
}