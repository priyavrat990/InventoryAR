package com.apisod.inventoryar.presentation.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.domain.usecase.inventory.InventoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryUseCases: InventoryUseCases
) : ViewModel() {

    private val _inventoryState =
        MutableStateFlow<UiState<List<InventoryItemEntity>>>(
            UiState.Loading
        )

    val inventoryState:
            StateFlow<UiState<List<InventoryItemEntity>>>
            = _inventoryState

    fun getInventory(
        roomId: String
    ) {

        viewModelScope.launch {

            inventoryUseCases
                .getInventoryUseCase(roomId)
                .collectLatest {

                    _inventoryState.value =
                        UiState.Success(it)
                }
        }
    }

    fun insertInventory(
        roomId: String,
        itemName: String
    ) {

        if (itemName.isBlank()) {
            return
        }

        viewModelScope.launch {

            val currentTime =
                System.currentTimeMillis()

            val item = InventoryItemEntity(
                itemId = UUID.randomUUID().toString(),
                roomOwnerId = roomId,
                itemName = itemName,
                itemCategory = "",
                itemCount = 1,
                confidenceScore = 0f,
                anchorPositionX = 0f,
                anchorPositionY = 0f,
                anchorPositionZ = 0f,
                rotation = 0f,
                imageThumbnail = "",
                detectedAt = currentTime,
                updatedAt = currentTime
            )

            inventoryUseCases
                .insertInventoryItemUseCase(item)
        }
    }
}