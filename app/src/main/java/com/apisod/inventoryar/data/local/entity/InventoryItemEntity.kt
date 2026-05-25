package com.apisod.inventoryar.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apisod.inventoryar.core.constants.DatabaseConstants

@Entity(
    tableName = DatabaseConstants.INVENTORY_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["roomId"],
            childColumns = ["roomOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["roomOwnerId"])
    ]
)
data class InventoryItemEntity(

    @PrimaryKey
    val itemId: String,

    val roomOwnerId: String,

    val itemName: String,

    val itemCategory: String,

    val itemCount: Int,

    val confidenceScore: Float,

    val anchorPositionX: Float,

    val anchorPositionY: Float,

    val anchorPositionZ: Float,

    val rotation: Float,

    val imageThumbnail: String,

    val detectedAt: Long,

    val updatedAt: Long
)