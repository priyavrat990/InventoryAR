package com.apisod.inventoryar.data.repository

import com.apisod.inventoryar.data.local.datasource.BuildingLocalDataSource
import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.domain.repository.BuildingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingRepositoryImpl @Inject constructor(
    private val localDataSource: BuildingLocalDataSource
) : BuildingRepository {

    override suspend fun insertBuilding(
        building: BuildingEntity
    ) {
        localDataSource.insertBuilding(building)
    }

    override suspend fun updateBuilding(
        building: BuildingEntity
    ) {
        localDataSource.updateBuilding(building)
    }

    override suspend fun deleteBuilding(
        building: BuildingEntity
    ) {
        localDataSource.deleteBuilding(building)
    }

    override fun getAllBuildings(): Flow<List<BuildingEntity>> {
        return localDataSource.getAllBuildings()
    }
}