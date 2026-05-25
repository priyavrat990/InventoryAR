package com.apisod.inventoryar.domain.usecase.building

data class BuildingUseCases(

    val insertBuildingUseCase: InsertBuildingUseCase,

    val updateBuildingUseCase: UpdateBuildingUseCase,

    val deleteBuildingUseCase: DeleteBuildingUseCase,

    val getBuildingsUseCase: GetBuildingsUseCase
)