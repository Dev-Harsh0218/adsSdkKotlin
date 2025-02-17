package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdRequest
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdResponse
import com.example.adsdk.domain.repository.GetRandomBannerAdRepository
class GetRandomBannerAdUseCase(private val repository: GetRandomBannerAdRepository) {
    suspend operator fun invoke(
        baseUrl:String,
        getRandomBannerAdRequest: GetRandomBannerAdRequest
    ): Result<GetRandomBannerAdResponse>{
       return repository.fetchRandomBannerAd(baseUrl,getRandomBannerAdRequest)
    }
}