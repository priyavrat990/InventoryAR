package com.apisod.inventoryar.data.local.dao

import androidx.room.*
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItem(
        item: InventoryItemEntity
    )

    @Update
    suspend fun updateInventoryItem(
        item: InventoryItemEntity
    )

    @Delete
    suspend fun deleteInventoryItem(
        item: InventoryItemEntity
    )

    @Query("SELECT * FROM inventory_items WHERE roomOwnerId = :roomId")
    fun getInventoryByRoom(
        roomId: String
    ): Flow<List<InventoryItemEntity>>
}