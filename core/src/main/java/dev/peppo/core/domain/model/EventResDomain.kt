package dev.peppo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventResDomain(
    val listEvents: List<Event>,
    val error: Boolean,
    val message: String
): Parcelable