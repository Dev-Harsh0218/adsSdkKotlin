package com.example.adsdk.domain.models.adModels.popup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class GetRandomPopupAdResponse(
    val message: String,
    val data : PopupAdData
)
@Serializable
data class PopupAdData(
    @SerialName("randomImage") val imageAsset: String,
    @SerialName("appurl") val appUrl:String,
    @SerialName("ad_id") val adId: String
)