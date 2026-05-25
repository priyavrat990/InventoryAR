package com.apisod.inventoryar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apisod.inventoryar.data.local.entity.RoomEntity
import com.apisod.inventoryar.databinding.ItemRoomBinding

class RoomAdapter(
    private val onRoomClick: (RoomEntity) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    private val roomList = mutableListOf<RoomEntity>()

    inner class RoomViewHolder(
        private val binding: ItemRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            room: RoomEntity
        ) {

            binding.tvRoomName.text =
                room.roomName

            binding.root.setOnClickListener {
                onRoomClick(room)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomViewHolder {

        val binding = ItemRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RoomViewHolder,
        position: Int
    ) {

        holder.bind(roomList[position])
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun submitList(
        list: List<RoomEntity>
    ) {

        roomList.clear()

        roomList.addAll(list)

        notifyDataSetChanged()
    }
}