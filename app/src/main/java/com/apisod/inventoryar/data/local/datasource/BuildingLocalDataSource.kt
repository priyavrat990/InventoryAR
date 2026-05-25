package com.apisod.inventoryar.data.local.datasource

import com.apisod.inventoryar.data.local.dao.BuildingDao
import com.apisod.inventoryar.data.local.entity.BuildingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildingLocalDataSource @Inject constructor(
    private val buildingDao: BuildingDao
) {

    suspend fun insertBuilding(
        building: BuildingEntity
    ) {
        buildingDao.insertBuilding(building)
    }

    suspend fun updateBuilding(
        building: BuildingEntity
    ) {
        buildingDao.updateBuilding(building)
    }

    suspend fun deleteBuilding(
        building: BuildingEntity
    ) {
        buildingDao.deleteBuilding(building)
    }

    fun getAllBuildings(): Flow<List<BuildingEntity>> {
        return buildingDao.getAllBuildings()
    }
}