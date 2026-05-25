package com.apisod.inventoryar.domain.repository

import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    suspend fun insertInventoryItem(
        item: InventoryItemEntity
    )

    suspend fun updateInventoryItem(
        item: InventoryItemEntity
    )

    suspend fun deleteInventoryItem(
        item: InventoryItemEntity
    )

    fun getInventoryByRoom(
        roomId: String
    ): Flow<List<InventoryItemEntity>>
}