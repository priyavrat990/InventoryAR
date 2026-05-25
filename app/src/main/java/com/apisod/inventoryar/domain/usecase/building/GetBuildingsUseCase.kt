package com.apisod.inventoryar.domain.usecase.building

import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.domain.repository.BuildingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBuildingsUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    operator fun invoke(): Flow<List<BuildingEntity>> {
        return repository.getAllBuildings()
    }
}