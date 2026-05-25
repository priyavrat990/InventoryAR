package com.apisod.inventoryar.data.local.datasource

import com.apisod.inventoryar.data.local.dao.InventoryDao
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryLocalDataSource @Inject constructor(
    private val inventoryDao: InventoryDao
) {

    suspend fun insertInventoryItem(
        item: InventoryItemEntity
    ) {
        inventoryDao.insertInventoryItem(item)
    }

    suspend fun updateInventoryItem(
        item: InventoryItemEntity
    ) {
        inventoryDao.updateInventoryItem(item)
    }

    suspend fun deleteInventoryItem(
        item: InventoryItemEntity
    ) {
        inventoryDao.deleteInventoryItem(item)
    }

    fun getInventoryByRoom(
        roomId: String
    ): Flow<List<InventoryItemEntity>> {
        return inventoryDao.getInventoryByRoom(roomId)
    }
}