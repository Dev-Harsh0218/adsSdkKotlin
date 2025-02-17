package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdRequest
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdResponse

interface GetRandomInterstitialAdRepository {
    suspend fun fetchRandomInterstitialAd(
        baseUrl: String,
        getRandomInterstitialAdRequest: GetRandomInterstitialAdRequest
    ): Result<GetRandomInterstitialAdResponse>
}