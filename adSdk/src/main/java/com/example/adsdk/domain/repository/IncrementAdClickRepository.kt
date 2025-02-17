package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickResponse

interface IncrementAdClickRepository {
    suspend fun incrementAdClick(
        baseUrl:String,
        incrementAdClickRequest: IncrementAdClickRequest
    ):Result<IncrementAdClickResponse>
}