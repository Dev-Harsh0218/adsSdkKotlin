package com.example.adsdk.domain.models.incrementAdClickModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class IncrementAdClickRequest(
    @SerialName("running_ad_id") val runningAdId: String,
)