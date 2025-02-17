package com.example.adsdk.domain.models.adModels.interstitial

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRandomInterstitialAdRequest(
    @SerialName("apk_unique_key") val apkUniqueKey: String
)