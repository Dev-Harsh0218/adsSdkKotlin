package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.appModels.RegisterAppRequest
import com.example.adsdk.domain.models.appModels.RegisterAppResponse

interface SdkInitializerRepository {
    suspend fun registerApp(
        baseUrl: String,
        registerAppRequest: RegisterAppRequest
    ): Result<RegisterAppResponse>
}