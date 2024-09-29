package com.b21dccn216.smarthome.network

import com.b21dccn216.smarthome.network.dto.DashboardDTO
import retrofit2.Response

class SmartHomeRepository(
    private val apiService: ApiService
) {
    suspend fun getDashboardData(limit: Int): DashboardDTO = apiService.getDashboardData(limit)

    suspend fun sendAction(device: String, state: String): Response<Void>{
        return apiService.sendAction(device, state)
    }
}