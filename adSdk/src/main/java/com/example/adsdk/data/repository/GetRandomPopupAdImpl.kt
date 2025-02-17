package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdRequest
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdResponse
import com.example.adsdk.domain.repository.GetRandomPopupAdRepository

class GetRandomPopupAdImpl (
    private val ktorClient: KtorClient,
): GetRandomPopupAdRepository{
    override suspend fun fetchRandomPopupAd(
        baseUrl: String,
        getRandomPopupAdRequest: GetRandomPopupAdRequest
    ): Result<GetRandomPopupAdResponse> {
        return runCatching {
            ktorClient.getRandomPopupAd(baseUrl,getRandomPopupAdRequest)
        }
    }
}