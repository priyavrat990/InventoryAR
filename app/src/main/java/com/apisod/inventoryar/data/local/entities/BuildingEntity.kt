package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "buildings"
)
data class BuildingEntity(

    @PrimaryKey
    val buildingId: String,

    val buildingName: String,

    val address: String,

    val createdAt: Long,

    val updatedAt: Long
)