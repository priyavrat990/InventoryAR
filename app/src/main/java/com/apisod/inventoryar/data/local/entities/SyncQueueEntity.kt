package com.apisod.inventoryar.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sync_queue"
)
data class SyncQueueEntity(

    @PrimaryKey
    val queueId: String,

    val entityType: String,

    val entityId: String,

    val operationType: String,

    val createdAt: Long,

    val retryCount: Int,

    val synced: Boolean
)