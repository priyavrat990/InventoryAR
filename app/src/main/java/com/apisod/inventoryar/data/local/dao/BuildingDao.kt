package com.apisod.inventoryar.data.local.dao

import androidx.room.*
import com.apisod.inventoryar.data.local.entities.BuildingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuilding(
        building: BuildingEntity
    )

    @Update
    suspend fun updateBuilding(
        building: BuildingEntity
    )

    @Delete
    suspend fun deleteBuilding(
        building: BuildingEntity
    )

    @Query("SELECT * FROM buildings ORDER BY updatedAt DESC")
    fun getBuildings():
            Flow<List<BuildingEntity>>

    @Query(
        """
        SELECT * FROM buildings
        WHERE buildingId = :buildingId
        """
    )
    suspend fun getBuildingById(
        buildingId: String
    ): BuildingEntity?
}