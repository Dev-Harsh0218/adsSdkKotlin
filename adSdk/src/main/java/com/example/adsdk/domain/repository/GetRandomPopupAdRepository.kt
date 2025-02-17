package com.example.adsdk.domain.repository

import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdRequest
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdResponse
interface GetRandomPopupAdRepository {
    suspend fun fetchRandomPopupAd(
        baseUrl:String,
        getRandomPopupAdRequest: GetRandomPopupAdRequest
    ):Result<GetRandomPopupAdResponse>
}