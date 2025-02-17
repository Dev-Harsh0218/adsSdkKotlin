package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickResponse
import com.example.adsdk.domain.repository.IncrementAdClickRepository
class IncrementAdClickUseCase (private val repository: IncrementAdClickRepository){
    suspend operator fun invoke(
        baseUrl:String,
        incrementAdClickRequest: IncrementAdClickRequest
    ):Result<IncrementAdClickResponse>{
        return repository.incrementAdClick(baseUrl,incrementAdClickRequest)
    }
}