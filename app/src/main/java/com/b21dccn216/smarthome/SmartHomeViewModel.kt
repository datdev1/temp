package com.b21dccn216.smarthome


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.b21dccn216.smarthome.network.SmartHomeRepository
import com.b21dccn216.smarthome.model.uistate.AppState.LOADED
import com.b21dccn216.smarthome.model.uistate.AppState.LOADING
import com.b21dccn216.smarthome.model.uistate.DashboarUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SmartHomeViewmodel(
    private val repository: SmartHomeRepository
) : ViewModel(){

    private var _uiStateDashboard = MutableStateFlow(DashboarUiState())
    val uiStateDashboard: StateFlow<DashboarUiState> = _uiStateDashboard.asStateFlow()

    private val _appState = MutableStateFlow(LOADING)
    val appState = _appState.asStateFlow()

    init {
        viewModelScope.launch {
            while(true) {
                getDashboardData()
                delay(1500)
            }
        }
    }
    private suspend fun getDashboardData(){
        try {
            val listResult = repository.getDashboardData(_uiStateDashboard.value.limitD.toInt())
            _uiStateDashboard.update {
                it.copy(data = listResult)
            }
            setAppStateLoaded()
            Log.d("viewmodel", listResult.toString())
        }catch (e: Exception){
            Log.e("viewmodel", e.toString())
        }
    }

    fun changeLimit(limit: String){
        _uiStateDashboard.update { it->
            it.copy(limitD = limit)
        }
    }

    fun clickAction(device: String, state: String){
        viewModelScope.launch {
            try {
                val response = repository.sendAction(device, state)
                if(response.isSuccessful && response.code() == 200){
                    _uiStateDashboard.update {
                        when(device){
                            "led" -> it.copy(data = it.data.copy(led = state))
                            "fan" -> it.copy(data = it.data.copy(fan = state))
                            "relay" -> it.copy(data = it.data.copy(relay = state))
                            else -> {it}
                        }
                    }
                    Log.d("viewmodel", state)
                    Log.d("viewmodel", response.toString())
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun setAppStateLoaded() {
        _appState.value = LOADED
    }


}


