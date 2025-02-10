package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.appModels.RegisterAppRequest
import com.example.adsdk.domain.models.appModels.RegisterAppResponse
import com.example.adsdk.domain.repository.SdkInitializerRepository

//SdkInitializerUseCase
class SdkInitializerUseCase(private val repository: SdkInitializerRepository) {
    suspend operator fun invoke(
        baseUrl: String,
        registerAppRequest: RegisterAppRequest
    ): Result<RegisterAppResponse> {
        return repository.registerApp(baseUrl, registerAppRequest)
    }
}