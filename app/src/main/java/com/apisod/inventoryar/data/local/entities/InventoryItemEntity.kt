package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "inventory_items",
    foreignKeys = [
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["roomId"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("roomId")
    ]
)
data class InventoryItemEntity(

    @PrimaryKey
    val itemId: String,

    val roomId: String,

    val itemName: String,

    val itemCategory: String,

    val itemCount: Int,

    val confidenceScore: Float,

    val anchorPositionX: Float,

    val anchorPositionY: Float,

    val anchorPositionZ: Float,

    val rotation: Float,

    val imageThumbnail: String?,

    val detectedAt: Long,

    val updatedAt: Long
)