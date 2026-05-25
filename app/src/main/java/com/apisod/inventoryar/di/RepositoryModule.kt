package com.apisod.inventoryar.di

import com.apisod.inventoryar.data.repository.BuildingRepositoryImpl
import com.apisod.inventoryar.data.repository.InventoryRepositoryImpl
import com.apisod.inventoryar.data.repository.RoomRepositoryImpl
import com.apisod.inventoryar.domain.repository.BuildingRepository
import com.apisod.inventoryar.domain.repository.InventoryRepository
import com.apisod.inventoryar.domain.repository.RoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBuildingRepository(
        impl: BuildingRepositoryImpl
    ): BuildingRepository

    @Binds
    @Singleton
    abstract fun bindRoomRepository(
        impl: RoomRepositoryImpl
    ): RoomRepository

    @Binds
    @Singleton
    abstract fun bindInventoryRepository(
        impl: InventoryRepositoryImpl
    ): InventoryRepository
}