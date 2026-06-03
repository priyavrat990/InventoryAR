package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rooms",
    foreignKeys = [
        ForeignKey(
            entity = BuildingEntity::class,
            parentColumns = ["buildingId"],
            childColumns = ["buildingId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("buildingId")
    ]
)
data class RoomEntity(

    @PrimaryKey
    val roomId: String,

    val buildingId: String,

    val roomName: String,

    val roomType: String,

    val dimensions: String,

    val createdAt: Long,

    val updatedAt: Long
)