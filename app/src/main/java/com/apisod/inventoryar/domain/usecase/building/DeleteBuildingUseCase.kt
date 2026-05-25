package com.apisod.inventoryar.domain.usecase.building

import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.domain.repository.BuildingRepository
import javax.inject.Inject

class DeleteBuildingUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    suspend operator fun invoke(
        building: BuildingEntity
    ) {
        repository.deleteBuilding(building)
    }
}