package com.example.adsdk.domain.models.incrementAdClickModels

import kotlinx.serialization.Serializable
@Serializable
data class IncrementAdClickResponse(
    val success: Boolean,
    val data: List<IncrementAdClickData>
)
@Serializable
data class IncrementAdClickData(
    val message:String,
    val id: String,
)