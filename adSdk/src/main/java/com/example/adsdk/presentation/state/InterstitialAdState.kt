package com.example.adsdk.presentation.state

import com.example.adsdk.domain.models.adModels.interstitial.InterstitialAdData

data class InterstitialAdState(
    val isLoading:Boolean = false,
    val interstitialAdData: InterstitialAdData? = null,
    val error: String? = null
)