package com.apisod.inventoryar.di

import android.content.Context
import androidx.room.Room
import com.apisod.inventoryar.core.constants.DatabaseConstants
import com.apisod.inventoryar.data.local.dao.BuildingDao
import com.apisod.inventoryar.data.local.dao.InventoryDao
import com.apisod.inventoryar.data.local.dao.RoomDao
import com.apisod.inventoryar.data.local.db.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): InventoryDatabase {

        return Room.databaseBuilder(
            context,
            InventoryDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideBuildingDao(
        database: InventoryDatabase
    ): BuildingDao {
        return database.buildingDao()
    }

    @Provides
    fun provideRoomDao(
        database: InventoryDatabase
    ): RoomDao {
        return database.roomDao()
    }

    @Provides
    fun provideInventoryDao(
        database: InventoryDatabase
    ): InventoryDao {
        return database.inventoryDao()
    }
}