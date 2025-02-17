package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionRequest
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionResponse
import com.example.adsdk.domain.repository.IncrementAdImpressionRepository

class IncrementAdImpressionUseCase(private val repository:IncrementAdImpressionRepository){
    suspend operator fun invoke(
        baseUrl:String,
        incrementAdImpressionRequest: IncrementAdImpressionRequest
    ): Result<IncrementAdImpressionResponse>{
        return repository.incrementAdImpression(baseUrl,incrementAdImpressionRequest)
    }
}