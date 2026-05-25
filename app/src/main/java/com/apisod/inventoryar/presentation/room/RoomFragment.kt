package com.apisod.inventoryar.presentation.room

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apisod.inventoryar.R
import com.apisod.inventoryar.core.ui.UiState
import com.apisod.inventoryar.databinding.FragmentRoomBinding
import com.apisod.inventoryar.presentation.adapter.RoomAdapter
import com.apisod.inventoryar.presentation.common.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomFragment :
    BaseFragment(R.layout.fragment_room) {

    private var _binding:
            FragmentRoomBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels()

    private lateinit var roomAdapter: RoomAdapter

    private lateinit var buildingId: String

    private lateinit var buildingName: String

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRoomBinding.bind(view)

        getArgumentsData()

        setupToolbar()

        setupRecyclerView()

        observeRooms()

        clickListeners()

        viewModel.getRooms(buildingId)
    }

    private fun getArgumentsData() {

        buildingId =
            arguments?.getString("buildingId")
                ?: ""

        buildingName =
            arguments?.getString("buildingName")
                ?: ""
    }

    private fun setupToolbar() {

        binding.tvToolbarTitle.text =
            buildingName
    }

    private fun setupRecyclerView() {

        roomAdapter = RoomAdapter {

            val bundle = Bundle().apply {

                putString("roomId", it.roomId)

                putString("roomName", it.roomName)
            }

            findNavController().navigate(
                R.id.action_roomFragment_to_inventoryFragment,
                bundle
            )
        }

        binding.rvRooms.apply {

            adapter = roomAdapter

            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }

    private fun observeRooms() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.roomState.collect {

                when (it) {

                    is UiState.Success -> {

                        roomAdapter.submitList(it.data)

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

        binding.fabAddRoom.setOnClickListener {

            showAddRoomDialog()
        }
    }

    private fun showAddRoomDialog() {

        val dialogView = layoutInflater.inflate(
            R.layout.dialog_add_room,
            null
        )

        val etRoomName =
            dialogView.findViewById<TextInputEditText>(
                R.id.etRoomName
            )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Room")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->

                val roomName =
                    etRoomName.text.toString()

                viewModel.insertRoom(
                    buildingId,
                    roomName
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