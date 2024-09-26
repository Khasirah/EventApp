package dev.peppo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailEventResDomain(
    val error: Boolean,
    val message: String,
    val event: Event
) : Parcelable