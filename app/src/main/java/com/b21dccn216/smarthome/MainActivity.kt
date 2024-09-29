package com.b21dccn216.smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.b21dccn216.smarthome.model.uistate.AppState
import com.b21dccn216.smarthome.network.ApiService
import com.b21dccn216.smarthome.network.SmartHomeRepository
import com.b21dccn216.smarthome.network.retrofit
import com.b21dccn216.smarthome.ui.screen.DashboardScreen
import com.b21dccn216.smarthome.ui.screen.LoadingScreen
import com.b21dccn216.smarthome.ui.theme.SmartHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = SmartHomeRepository(retrofit.create(ApiService::class.java))
        val viewmodel = SmartHomeViewmodel(repository = repository)
        setContent {
            SmartHomeTheme {
                DashboardScreen(viewmodel)
            }
        }
    }
}
