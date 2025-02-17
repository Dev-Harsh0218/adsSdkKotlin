package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdRequest
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdResponse
import com.example.adsdk.domain.repository.GetRandomInterstitialAdRepository
class GetRandomInterstitialAdUseCase (private val repository: GetRandomInterstitialAdRepository){
    suspend operator fun invoke(
        baseUrl:String,
        getRandomInterstitialAdRequest: GetRandomInterstitialAdRequest
    ): Result<GetRandomInterstitialAdResponse>{
       return repository.fetchRandomInterstitialAd(baseUrl,getRandomInterstitialAdRequest)
    }
}