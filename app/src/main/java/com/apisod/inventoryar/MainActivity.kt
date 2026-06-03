package com.apisod.inventoryar

import android.os.Bundle
import com.apisod.inventoryar.common.CentralizedActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    CentralizedActivity() {

    private lateinit var bottomNavigation:
            BottomNavigationView

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        setContentView(
            R.layout.activity_main
        )

        initViews()
    }

    private fun initViews() {
        bottomNavigation =
            findViewById(
                R.id.bottomNavigation
            )
    }

}