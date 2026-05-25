package com.apisod.inventoryar.data.local.datasource

import com.apisod.inventoryar.data.local.dao.RoomDao
import com.apisod.inventoryar.data.local.entity.RoomEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val roomDao: RoomDao
) {

    suspend fun insertRoom(
        room: RoomEntity
    ) {
        roomDao.insertRoom(room)
    }

    suspend fun updateRoom(
        room: RoomEntity
    ) {
        roomDao.updateRoom(room)
    }

    suspend fun deleteRoom(
        room: RoomEntity
    ) {
        roomDao.deleteRoom(room)
    }

    fun getRoomsByBuilding(
        buildingId: String
    ): Flow<List<RoomEntity>> {
        return roomDao.getRoomsByBuilding(buildingId)
    }
}