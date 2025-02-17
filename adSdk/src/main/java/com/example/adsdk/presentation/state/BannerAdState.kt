package com.example.adsdk.presentation.state

import com.example.adsdk.domain.models.adModels.banner.BannerAdData

data class BannerAdState(
    val isLoading:Boolean = false,
    val bannerAdData: BannerAdData? = null,
    val error:String?= null
)