package com.example.adsdk.domain.usecase

import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdRequest
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdResponse
import com.example.adsdk.domain.repository.GetRandomPopupAdRepository

class GetRandomPopupAdUseCase(private val repository: GetRandomPopupAdRepository){
    suspend operator fun invoke(
        baseUrl:String,
        getRandomPopupAdRequest: GetRandomPopupAdRequest
    ): Result<GetRandomPopupAdResponse>{
       return repository.fetchRandomPopupAd(baseUrl,getRandomPopupAdRequest)
    }
}