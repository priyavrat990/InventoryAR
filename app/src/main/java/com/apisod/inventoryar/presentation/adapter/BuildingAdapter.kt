package com.apisod.inventoryar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apisod.inventoryar.data.local.entity.BuildingEntity
import com.apisod.inventoryar.databinding.ItemBuildingBinding

class BuildingAdapter(
    private val onBuildingClick: (BuildingEntity) -> Unit,
    private val onDeleteClick: (BuildingEntity) -> Unit
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    private val buildingList = mutableListOf<BuildingEntity>()

    inner class BuildingViewHolder(
        private val binding: ItemBuildingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            building: BuildingEntity
        ) {

            binding.tvBuildingName.text =
                building.buildingName

            binding.tvBuildingAddress.text =
                building.address

            binding.root.setOnClickListener {
                onBuildingClick(building)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(building)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuildingViewHolder {

        val binding = ItemBuildingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BuildingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BuildingViewHolder,
        position: Int
    ) {

        holder.bind(buildingList[position])
    }

    override fun getItemCount(): Int {
        return buildingList.size
    }

    fun submitList(
        list: List<BuildingEntity>
    ) {

        buildingList.clear()

        buildingList.addAll(list)

        notifyDataSetChanged()
    }
}