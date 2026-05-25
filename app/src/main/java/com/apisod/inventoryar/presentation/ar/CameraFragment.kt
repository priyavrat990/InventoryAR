package com.apisod.inventoryar.presentation.ar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.apisod.inventoryar.R
import com.apisod.inventoryar.core.tflite.InventoryFrameAnalyzer
import com.apisod.inventoryar.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors

@AndroidEntryPoint
class CameraFragment :
    BaseFragment(R.layout.fragment_camera) {

    private val viewModel:
            CameraViewModel by viewModels()

    private lateinit var previewView:
            View

    private lateinit var tvCameraStatus:
            TextView

    private val cameraExecutor =
        Executors.newSingleThreadExecutor()

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {

                startCamera()

            } else {

                tvCameraStatus.text =
                    "Camera Permission Denied"
            }
        }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        previewView =
            view.findViewById(
                R.id.cameraPreviewView
            )

        tvCameraStatus =
            view.findViewById(
                R.id.tvCameraStatus
            )

        checkPermission()
    }

    private fun checkPermission() {

        if (
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            startCamera()

        } else {

            permissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    private fun startCamera() {

        tvCameraStatus.text =
            "Starting Camera..."

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(
                requireContext()
            )

        cameraProviderFuture.addListener({

            try {

                val cameraProvider =
                    cameraProviderFuture.get()

                val preview =
                    Preview.Builder()
                        .build()

                val cameraPreview =
                    previewView as androidx.camera.view.PreviewView

                preview.setSurfaceProvider(
                    cameraPreview.surfaceProvider
                )

                val imageAnalysis =
                    ImageAnalysis.Builder()
                        .setBackpressureStrategy(
                            ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                        )
                        .build()

                imageAnalysis.setAnalyzer(
                    cameraExecutor,
                    InventoryFrameAnalyzer(
                        requireContext()
                    )
                )

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageAnalysis
                )

                tvCameraStatus.text =
                    "Camera Running"

                Log.d(
                    "CAMERA_PIPELINE",
                    "Camera started successfully"
                )

            } catch (e: Exception) {

                e.printStackTrace()

                tvCameraStatus.text =
                    "Camera Failed"

                Log.e(
                    "CAMERA_PIPELINE",
                    "Camera startup failed",
                    e
                )
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        cameraExecutor.shutdown()
    }
}