package com.apisod.inventoryar.presentation.building

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.domain.usecase.building.BuildingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BuildingViewModel @Inject constructor(
    private val buildingUseCases: BuildingUseCases
) : ViewModel() {

    private val _buildingState =
        MutableStateFlow<UiState<List<BuildingEntity>>>(
            UiState.Loading
        )

    val buildingState:
            StateFlow<UiState<List<BuildingEntity>>>
            = _buildingState

    init {
        getBuildings()
    }

    private fun getBuildings() {

        viewModelScope.launch {

            buildingUseCases
                .getBuildingsUseCase()
                .collectLatest { buildings ->

                    _buildingState.value =
                        UiState.Success(buildings)
                }
        }
    }

    fun insertBuilding(
        buildingName: String,
        address: String
    ) {

        if (buildingName.isBlank()) {
            return
        }

        viewModelScope.launch {

            val currentTime = System.currentTimeMillis()

            val building = BuildingEntity(
                buildingId = UUID.randomUUID().toString(),
                buildingName = buildingName,
                address = address,
                createdAt = currentTime,
                updatedAt = currentTime
            )

            buildingUseCases
                .insertBuildingUseCase(building)
        }
    }

    fun deleteBuilding(
        building: BuildingEntity
    ) {

        viewModelScope.launch {

            buildingUseCases
                .deleteBuildingUseCase(building)
        }
    }
}