package com.example.adsdk.domain.models.adModels.popup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRandomPopupAdRequest(
    @SerialName("apk_unique_key") val apkUniqueKey: String
)