package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdRequest
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdResponse

interface GetRandomBannerAdRepository {
    suspend fun fetchRandomBannerAd(
        baseUrl:String,
        getRandomBannerAdRequest: GetRandomBannerAdRequest
    ):Result<GetRandomBannerAdResponse>
}