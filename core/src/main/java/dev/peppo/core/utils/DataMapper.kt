package dev.peppo.core.utils

import dev.peppo.core.data.remote.response.DetailEventResponse
import dev.peppo.core.domain.model.DetailEventResDomain
import dev.peppo.core.domain.model.EventResDomain
import dev.peppo.core.data.local.entity.Event as EventEntity
import dev.peppo.core.data.remote.response.Event as EventResponse
import dev.peppo.core.domain.model.Event as EventDomain
import dev.peppo.core.data.remote.response.EventResponse as ResponseAllEvent

object DataMapper {
    fun mapDetailEventResToEntities(input: DetailEventResponse): DetailEventResDomain {
        return DetailEventResDomain(
            error = input.error,
            message = input.message,
            event = mapResponseToDomain(input.event)
        )
    }

    fun mapEventResToEntities(input: ResponseAllEvent): EventResDomain {
        return EventResDomain(
            error = input.error,
            message = input.message,
            listEvents = input.listEvents.map {
                EventDomain(
                    summary = it.summary,
                    mediaCover = it.mediaCover,
                    registrants = it.registrants,
                    imageLogo = it.imageLogo,
                    link = it.link,
                    description = it.description,
                    ownerName = it.ownerName,
                    cityName = it.cityName,
                    quota = it.quota,
                    name = it.name,
                    id = it.id,
                    beginTime = it.beginTime,
                    endTime = it.endTime,
                    category = it.category
                )
            }
        )
    }

    fun mapEntitiesToDomain(input: List<EventEntity>): List<EventDomain> {
        return input.map {
            EventDomain(
                summary = it.summary,
                mediaCover = it.mediaCover,
                registrants = 0,
                imageLogo = "",
                link = "",
                description = "",
                ownerName = "",
                cityName = "",
                quota = 0,
                name = it.name,
                id = it.id,
                beginTime = "",
                endTime = "",
                category = ""
            )
        }
    }

    fun mapDomainToEntities(input: EventDomain): EventEntity {
        return EventEntity(
            id = input.id,
            name = input.name,
            mediaCover = input.mediaCover,
            summary = input.summary
        )
    }

    fun mapResponseToDomain(input: EventResponse): EventDomain {
        return EventDomain(
            summary = input.summary,
            mediaCover = input.mediaCover,
            registrants = input.registrants,
            imageLogo = input.imageLogo,
            link = input.link,
            description = input.description,
            ownerName = input.ownerName,
            cityName = input.cityName,
            quota = input.quota,
            name = input.name,
            id = input.id,
            beginTime = input.beginTime,
            endTime = input.endTime,
            category = input.category
        )
    }
}