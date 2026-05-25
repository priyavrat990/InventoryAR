package com.apisod.inventoryar.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apisod.inventoryar.data.local.dao.BuildingDao
import com.apisod.inventoryar.data.local.dao.InventoryDao
import com.apisod.inventoryar.data.local.dao.RoomDao
import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.data.local.entity.RoomEntity

@Database(
    entities = [
        BuildingEntity::class,
        RoomEntity::class,
        InventoryItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

    abstract fun roomDao(): RoomDao

    abstract fun inventoryDao(): InventoryDao
}