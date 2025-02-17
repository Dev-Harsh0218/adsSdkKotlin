package com.example.adsdk.presentation.state

import com.example.adsdk.domain.models.adModels.popup.PopupAdData

data class PopupAdState(
    val isLoading:Boolean = false,
    val popupAdData: PopupAdData? = null,
    val error:String?=null
)