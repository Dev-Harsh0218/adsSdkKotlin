package com.example.adsdk.domain.models.adModels.banner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRandomBannerAdResponse (
    val message:String,
    val data:BannerAdData
)

@Serializable
data class BannerAdData(
    @SerialName("randomImage") val imageAsset:String,
    @SerialName("appurl") val appUrl:String,
    @SerialName("ad_id") val adId:String
)