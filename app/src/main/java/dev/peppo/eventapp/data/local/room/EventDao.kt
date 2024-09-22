package dev.peppo.eventapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.peppo.eventapp.data.local.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM Event ORDER BY id ASC")
    fun getAllEvent(): Flow<List<Event>>

    @Query("SELECT EXISTS(SELECT * FROM Event WHERE id = :id)")
    fun isFavouriteEvent(id: Int): Flow<Boolean>
}