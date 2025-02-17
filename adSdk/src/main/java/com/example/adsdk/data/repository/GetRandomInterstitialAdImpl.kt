package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdRequest
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdResponse
import com.example.adsdk.domain.repository.GetRandomInterstitialAdRepository

class GetRandomInterstitialAdImpl(
    private val ktorClient:KtorClient,
):GetRandomInterstitialAdRepository{
    override suspend fun fetchRandomInterstitialAd(
        baseUrl: String,
        getRandomInterstitialAdRequest: GetRandomInterstitialAdRequest
    ): Result<GetRandomInterstitialAdResponse> {
        return runCatching {
            ktorClient.getRandomInterstitialAd(baseUrl,getRandomInterstitialAdRequest)
        }
    }
}