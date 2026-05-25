package com.apisod.inventoryar.domain.repository

import com.apisod.inventoryar.data.local.entity.RoomEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun insertRoom(
        room: RoomEntity
    )

    suspend fun updateRoom(
        room: RoomEntity
    )

    suspend fun deleteRoom(
        room: RoomEntity
    )

    fun getRoomsByBuilding(
        buildingId: String
    ): Flow<List<RoomEntity>>
}