package com.example.adsdk.domain.models.adModels.banner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRandomBannerAdRequest(
    @SerialName("apk_unique_key") val apkUniqueKey: String
)