package com.apisod.inventoryar.presentation.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.data.local.entity.RoomEntity
import com.apisod.inventoryar.domain.usecase.room.RoomUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomUseCases: RoomUseCases
) : ViewModel() {

    private val _roomState =
        MutableStateFlow<UiState<List<RoomEntity>>>(
            UiState.Loading
        )

    val roomState:
            StateFlow<UiState<List<RoomEntity>>>
            = _roomState

    fun getRooms(
        buildingId: String
    ) {

        viewModelScope.launch {

            roomUseCases
                .getRoomsUseCase(buildingId)
                .collectLatest {

                    _roomState.value =
                        UiState.Success(it)
                }
        }
    }

    fun insertRoom(
        buildingId: String,
        roomName: String
    ) {

        if (roomName.isBlank()) {
            return
        }

        viewModelScope.launch {

            val currentTime =
                System.currentTimeMillis()

            val room = RoomEntity(
                roomId = UUID.randomUUID().toString(),
                buildingOwnerId = buildingId,
                roomName = roomName,
                roomType = "",
                dimensions = "",
                createdAt = currentTime,
                updatedAt = currentTime
            )

            roomUseCases
                .insertRoomUseCase(room)
        }
    }
}