package com.apisod.inventoryar.data.repository

import com.apisod.inventoryar.data.local.datasource.InventoryLocalDataSource
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val localDataSource: InventoryLocalDataSource
) : InventoryRepository {

    override suspend fun insertInventoryItem(
        item: InventoryItemEntity
    ) {
        localDataSource.insertInventoryItem(item)
    }

    override suspend fun updateInventoryItem(
        item: InventoryItemEntity
    ) {
        localDataSource.updateInventoryItem(item)
    }

    override suspend fun deleteInventoryItem(
        item: InventoryItemEntity
    ) {
        localDataSource.deleteInventoryItem(item)
    }

    override fun getInventoryByRoom(
        roomId: String
    ): Flow<List<InventoryItemEntity>> {
        return localDataSource.getInventoryByRoom(roomId)
    }
}