package com.apisod.inventoryar.domain.usecase.inventory

data class InventoryUseCases(

    val insertInventoryItemUseCase: InsertInventoryItemUseCase,

    val getInventoryUseCase: GetInventoryUseCase
)