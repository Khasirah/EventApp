package dev.peppo.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.peppo.core.data.local.entity.Event

@Database(entities = [Event::class], version = 1)
abstract class EventRoomDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}