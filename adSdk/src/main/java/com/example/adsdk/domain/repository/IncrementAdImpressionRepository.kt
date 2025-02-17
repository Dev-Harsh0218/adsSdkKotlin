package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionRequest
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionResponse
interface IncrementAdImpressionRepository {
    suspend fun incrementAdImpression(
        baseUrl:String,
        incrementAdImpressionRequest: IncrementAdImpressionRequest
    ):Result<IncrementAdImpressionResponse>
}