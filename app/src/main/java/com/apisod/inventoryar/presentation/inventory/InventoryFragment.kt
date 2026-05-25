package com.apisod.inventoryar.presentation.inventory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apisod.inventoryar.R
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.databinding.FragmentInventoryBinding
import com.apisod.inventoryar.presentation.adapter.InventoryAdapter
import com.apisod.inventoryar.presentation.common.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InventoryFragment :
    BaseFragment(R.layout.fragment_inventory) {

    private var _binding:
            FragmentInventoryBinding? = null

    private val binding get() = _binding!!

    private val viewModel: InventoryViewModel by viewModels()

    private lateinit var inventoryAdapter: InventoryAdapter

    private lateinit var roomId: String

    private lateinit var roomName: String

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentInventoryBinding.bind(view)

        getArgumentsData()

        setupToolbar()

        setupRecyclerView()

        observeInventory()

        clickListeners()

        viewModel.getInventory(roomId)
    }

    private fun getArgumentsData() {

        roomId =
            arguments?.getString("roomId")
                ?: ""

        roomName =
            arguments?.getString("roomName")
                ?: ""
    }

    private fun setupToolbar() {

        binding.tvToolbarTitle.text =
            roomName
    }

    private fun setupRecyclerView() {

        inventoryAdapter = InventoryAdapter()

        binding.rvInventory.apply {

            adapter = inventoryAdapter

            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }

    private fun observeInventory() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.inventoryState.collect {

                when (it) {

                    is UiState.Success -> {

                        inventoryAdapter.submitList(it.data)

                        binding.tvEmpty.visibility =
                            if (it.data.isEmpty())
                                View.VISIBLE
                            else
                                View.GONE
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun clickListeners() {

        binding.fabAddInventory.setOnClickListener {

            showAddInventoryDialog()
        }

        binding.btnOpenCamera.setOnClickListener {

            findNavController().navigate(
                R.id.action_inventoryFragment_to_cameraFragment
            )
        }
    }

    private fun showAddInventoryDialog() {

        val dialogView = layoutInflater.inflate(
            R.layout.dialog_add_inventory,
            null
        )

        val etItemName =
            dialogView.findViewById<TextInputEditText>(
                R.id.etItemName
            )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Inventory Item")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->

                val itemName =
                    etItemName.text.toString()

                viewModel.insertInventory(
                    roomId,
                    itemName
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}