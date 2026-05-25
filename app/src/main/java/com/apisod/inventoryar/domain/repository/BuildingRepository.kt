package com.apisod.inventoryar.domain.repository

import com.apisod.inventoryar.data.local.entity.BuildingEntity
import kotlinx.coroutines.flow.Flow

interface BuildingRepository {

    suspend fun insertBuilding(
        building: BuildingEntity
    )

    suspend fun updateBuilding(
        building: BuildingEntity
    )

    suspend fun deleteBuilding(
        building: BuildingEntity
    )

    fun getAllBuildings(): Flow<List<BuildingEntity>>
}