package com.apisod.inventoryar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apisod.inventoryar.core.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.BUILDING_TABLE)
data class BuildingEntity(
    @PrimaryKey
    val buildingId: String,

    val buildingName: String,

    val address: String,

    val createdAt: Long,

    val updatedAt: Long
)