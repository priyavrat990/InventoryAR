package com.apisod.inventoryar.domain.usecase.inventory

import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.domain.repository.InventoryRepository
import javax.inject.Inject

class InsertInventoryItemUseCase @Inject constructor(
    private val repository: InventoryRepository
) {

    suspend operator fun invoke(
        item: InventoryItemEntity
    ) {
        repository.insertInventoryItem(item)
    }
}