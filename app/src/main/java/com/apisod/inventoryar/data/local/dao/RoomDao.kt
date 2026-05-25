package com.apisod.inventoryar.data.local.dao

import androidx.room.*
import com.apisod.inventoryar.data.local.entity.RoomEntity
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

    @Query("SELECT * FROM rooms WHERE buildingOwnerId = :buildingId")
    fun getRoomsByBuilding(
        buildingId: String
    ): Flow<List<RoomEntity>>
}