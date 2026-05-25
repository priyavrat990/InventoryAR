package com.apisod.inventoryar.domain.usecase.room

import com.apisod.inventoryar.data.local.entity.RoomEntity
import com.apisod.inventoryar.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: RoomRepository
) {

    operator fun invoke(
        buildingId: String
    ): Flow<List<RoomEntity>> {

        return repository.getRoomsByBuilding(buildingId)
    }
}