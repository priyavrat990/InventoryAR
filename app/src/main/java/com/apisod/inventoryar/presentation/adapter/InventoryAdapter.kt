package com.apisod.inventoryar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apisod.inventoryar.data.local.entity.InventoryItemEntity
import com.apisod.inventoryar.databinding.ItemInventoryBinding

class InventoryAdapter :
    RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    private val inventoryList =
        mutableListOf<InventoryItemEntity>()

    inner class InventoryViewHolder(
        private val binding: ItemInventoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: InventoryItemEntity
        ) {

            binding.tvItemName.text =
                item.itemName

            binding.tvItemCount.text =
                "Count: ${item.itemCount}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InventoryViewHolder {

        val binding = ItemInventoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: InventoryViewHolder,
        position: Int
    ) {

        holder.bind(inventoryList[position])
    }

    override fun getItemCount(): Int {
        return inventoryList.size
    }

    fun submitList(
        list: List<InventoryItemEntity>
    ) {

        inventoryList.clear()

        inventoryList.addAll(list)

        notifyDataSetChanged()
    }
}