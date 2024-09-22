package dev.peppo.eventapp.data.repository

import dev.peppo.eventapp.data.local.room.EventDao
import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.remote.response.EventResponse
import dev.peppo.eventapp.data.remote.retrofit.ApiService
import dev.peppo.eventapp.domain.repository.IEventRepository
import dev.peppo.eventapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import dev.peppo.eventapp.domain.model.Event as EventDomain

class EventsRepository(
    private val apiService: ApiService,
    private val eventDao: EventDao
): IEventRepository {
    override suspend fun getAllEvent(): Flow<EventResponse> {
        return flowOf(apiService.getAllEvent())
    }

    override suspend fun getDetailEvent(eventId: Int): Flow<DetailEventResponse> {
        return flowOf(apiService.getDetailEvent(eventId))
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

//    companion object {
//        @Volatile
//        private var instance: EventsRepository? = null
//        fun getInstance(
//            apiService: ApiService,
//            eventDao: EventDao
//        ): EventsRepository = instance ?: synchronized(this) {
//            instance ?: EventsRepository(apiService, eventDao)
//        }.also { instance = it }
//    }
}