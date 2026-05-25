package com.apisod.inventoryar.data.repository

import com.apisod.inventoryar.data.local.datasource.RoomLocalDataSource
import com.apisod.inventoryar.data.local.entity.RoomEntity
import com.apisod.inventoryar.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val localDataSource: RoomLocalDataSource
) : RoomRepository {

    override suspend fun insertRoom(
        room: RoomEntity
    ) {
        localDataSource.insertRoom(room)
    }

    override suspend fun updateRoom(
        room: RoomEntity
    ) {
        localDataSource.updateRoom(room)
    }

    override suspend fun deleteRoom(
        room: RoomEntity
    ) {
        localDataSource.deleteRoom(room)
    }

    override fun getRoomsByBuilding(
        buildingId: String
    ): Flow<List<RoomEntity>> {
        return localDataSource.getRoomsByBuilding(buildingId)
    }
}