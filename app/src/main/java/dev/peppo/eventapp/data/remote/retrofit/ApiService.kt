package dev.peppo.eventapp.data.remote.retrofit

import dev.peppo.eventapp.data.remote.response.DetailEventResponse
import dev.peppo.eventapp.data.remote.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("events")
    suspend fun getAllEvent() : EventResponse

    @GET("events/{id}")
    suspend fun getDetailEvent(
        @Path("id") eventId: Int
    ) : DetailEventResponse
}