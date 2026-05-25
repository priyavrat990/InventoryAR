package com.apisod.inventoryar.domain.usecase.inventory

import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryUseCase @Inject constructor(
    private val repository: InventoryRepository
) {

    operator fun invoke(
        roomId: String
    ): Flow<List<InventoryItemEntity>> {

        return repository.getInventoryByRoom(roomId)
    }
}