package com.apisod.inventoryar.di

import com.apisod.inventoryar.domain.repository.BuildingRepository
import com.apisod.inventoryar.domain.repository.InventoryRepository
import com.apisod.inventoryar.domain.repository.RoomRepository
import com.apisod.inventoryar.domain.usecase.building.*
import com.apisod.inventoryar.domain.usecase.inventory.GetInventoryUseCase
import com.apisod.inventoryar.domain.usecase.inventory.InsertInventoryItemUseCase
import com.apisod.inventoryar.domain.usecase.inventory.InventoryUseCases
import com.apisod.inventoryar.domain.usecase.room.GetRoomsUseCase
import com.apisod.inventoryar.domain.usecase.room.InsertRoomUseCase
import com.apisod.inventoryar.domain.usecase.room.RoomUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideBuildingUseCases(
        repository: BuildingRepository
    ): BuildingUseCases {

        return BuildingUseCases(
            insertBuildingUseCase = InsertBuildingUseCase(repository),
            updateBuildingUseCase = UpdateBuildingUseCase(repository),
            deleteBuildingUseCase = DeleteBuildingUseCase(repository),
            getBuildingsUseCase = GetBuildingsUseCase(repository)
        )
    }

    @Provides
    fun provideRoomUseCases(
        repository: RoomRepository
    ): RoomUseCases {

        return RoomUseCases(
            insertRoomUseCase = InsertRoomUseCase(repository),
            getRoomsUseCase = GetRoomsUseCase(repository)
        )
    }

    @Provides
    fun provideInventoryUseCases(
        repository: InventoryRepository
    ): InventoryUseCases {

        return InventoryUseCases(
            insertInventoryItemUseCase =
                InsertInventoryItemUseCase(repository),

            getInventoryUseCase =
                GetInventoryUseCase(repository)
        )
    }
}