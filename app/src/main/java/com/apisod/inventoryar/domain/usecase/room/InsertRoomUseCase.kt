package com.apisod.inventoryar.domain.usecase.room

import com.apisod.inventoryar.data.local.entity.RoomEntity
import com.apisod.inventoryar.domain.repository.RoomRepository
import javax.inject.Inject

class InsertRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) {

    suspend operator fun invoke(
        room: RoomEntity
    ) {
        repository.insertRoom(room)
    }
}