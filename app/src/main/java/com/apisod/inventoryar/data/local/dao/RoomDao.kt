package com.apisod.inventoryar.data.local.dao

import androidx.room.*
import com.apisod.inventoryar.data.local.entities.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(
        room: RoomEntity
    )

    @Update
    suspend fun updateRoom(
        room: RoomEntity
    )

    @Delete
    suspend fun deleteRoom(
        room: RoomEntity
    )

    @Query(
        """
        SELECT * FROM rooms
        WHERE buildingId = :buildingId
        ORDER BY updatedAt DESC
        """
    )
    fun getRoomsByBuilding(
        buildingId: String
    ): Flow<List<RoomEntity>>
}