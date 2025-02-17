package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdRequest
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdResponse
import com.example.adsdk.domain.repository.GetRandomBannerAdRepository

class GetRandomBannerAdImpl(
    private val ktorClient: KtorClient,
): GetRandomBannerAdRepository{
    override suspend fun fetchRandomBannerAd(
        baseUrl: String,
        getRandomBannerAdRequest: GetRandomBannerAdRequest
    ): Result<GetRandomBannerAdResponse> {
        return runCatching {
            ktorClient.getRandomBannerAd(baseUrl,getRandomBannerAdRequest)
        }
    }
}