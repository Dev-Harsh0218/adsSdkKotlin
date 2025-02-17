package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionRequest
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionResponse
import com.example.adsdk.domain.repository.IncrementAdImpressionRepository

class IncrementAdImpressionImpl(
    private val ktorClient: KtorClient
):IncrementAdImpressionRepository{
    override suspend fun incrementAdImpression(
        baseUrl: String,
        incrementAdImpressionRequest: IncrementAdImpressionRequest
    ): Result<IncrementAdImpressionResponse> {
        return runCatching {
            ktorClient.incrementAdImpression(baseUrl,incrementAdImpressionRequest)
        }
    }
}