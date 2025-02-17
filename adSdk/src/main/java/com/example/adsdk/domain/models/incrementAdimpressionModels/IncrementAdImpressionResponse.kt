package com.example.adsdk.domain.models.incrementAdimpressionModels

import kotlinx.serialization.Serializable
@Serializable
data class IncrementAdImpressionResponse(
    val success: Boolean,
    val data: List<IncrementAdImpressionData>
)
@Serializable
data class IncrementAdImpressionData(
    val message:String,
    val id:String
)