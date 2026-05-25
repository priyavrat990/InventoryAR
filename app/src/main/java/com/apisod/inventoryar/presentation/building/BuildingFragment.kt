package com.apisod.inventoryar.presentation.building

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apisod.inventoryar.R
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.databinding.FragmentBuildingBinding
import com.apisod.inventoryar.presentation.adapter.BuildingAdapter
import com.apisod.inventoryar.presentation.common.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuildingFragment :
    BaseFragment(R.layout.fragment_building) {

    private var _binding:
            FragmentBuildingBinding? = null

    private val binding get() = _binding!!

    private val viewModel: BuildingViewModel by viewModels()

    private lateinit var buildingAdapter: BuildingAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBuildingBinding.bind(view)

        setupRecyclerView()

        observeBuildings()

        clickListeners()
    }

    private fun setupRecyclerView() {

        buildingAdapter = BuildingAdapter(

            onBuildingClick = {

                val bundle = Bundle().apply {

                    putString("buildingId", it.buildingId)

                    putString("buildingName", it.buildingName)
                }

                findNavController().navigate(
                    R.id.action_buildingFragment_to_roomFragment,
                    bundle
                )
            },

            onDeleteClick = {

                viewModel.deleteBuilding(it)
            }
        )

        binding.rvBuildings.apply {

            adapter = buildingAdapter

            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }

    private fun observeBuildings() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.buildingState.collect {

                when (it) {

                    is UiState.Success -> {

                        buildingAdapter.submitList(it.data)

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

        binding.fabAddBuilding.setOnClickListener {

            showAddBuildingDialog()
        }
    }

    private fun showAddBuildingDialog() {

        val dialogView = layoutInflater.inflate(
            R.layout.dialog_add_building,
            null
        )

        val etBuildingName =
            dialogView.findViewById<TextInputEditText>(
                R.id.etBuildingName
            )

        val etBuildingAddress =
            dialogView.findViewById<TextInputEditText>(
                R.id.etBuildingAddress
            )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Building")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->

                val buildingName =
                    etBuildingName.text.toString()

                val address =
                    etBuildingAddress.text.toString()

                viewModel.insertBuilding(
                    buildingName,
                    address
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