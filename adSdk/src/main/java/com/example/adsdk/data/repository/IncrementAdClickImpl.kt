package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickResponse
import com.example.adsdk.domain.repository.IncrementAdClickRepository

class IncrementAdClickImpl (
    private val ktorClient: KtorClient
):IncrementAdClickRepository{
    override suspend fun incrementAdClick(
        baseUrl: String,
        incrementAdClickRequest: IncrementAdClickRequest
    ): Result<IncrementAdClickResponse> {
        return runCatching {
             ktorClient.incrementAdClick(baseUrl,incrementAdClickRequest)
        }
    }
}