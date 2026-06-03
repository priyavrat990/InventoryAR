package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scan_sessions",
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
data class ScanSessionEntity(

    @PrimaryKey
    val sessionId: String,

    val roomId: String,

    val startedAt: Long,

    val endedAt: Long,

    val totalObjectsDetected: Int,

    val scanStatus: String
)