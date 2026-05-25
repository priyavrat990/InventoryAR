package com.apisod.inventoryar.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apisod.inventoryar.core.constants.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.ROOM_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = BuildingEntity::class,
            parentColumns = ["buildingId"],
            childColumns = ["buildingOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["buildingOwnerId"])
    ]
)
data class RoomEntity(

    @PrimaryKey
    val roomId: String,

    val buildingOwnerId: String,

    val roomName: String,

    val roomType: String,

    val dimensions: String,

    val createdAt: Long,

    val updatedAt: Long
)