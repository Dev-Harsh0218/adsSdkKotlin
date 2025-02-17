package com.example.adsdk.domain.models.adModels.interstitial

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRandomInterstitialAdResponse(
    val message:String,
    val data: InterstitialAdData
)

@Serializable
data class InterstitialAdData(
    @SerialName("randomImage") val imageAsset:String,
    @SerialName("appurl") val appUrl:String,
    @SerialName("ad_id") val adId:String
)