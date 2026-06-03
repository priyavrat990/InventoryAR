package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "anchors",
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
data class AnchorEntity(

    @PrimaryKey
    val anchorId: String,

    val roomId: String,

    val posX: Float,

    val posY: Float,

    val posZ: Float,

    val rotationX: Float,

    val rotationY: Float,

    val rotationZ: Float,

    val createdAt: Long
)