package com.example.adsdk.domain.models.incrementAdimpressionModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IncrementAdImpressionRequest(
    @SerialName("running_ad_id") val runningAdId: String,
)