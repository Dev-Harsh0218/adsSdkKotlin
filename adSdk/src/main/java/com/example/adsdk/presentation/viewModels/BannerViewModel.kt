package com.example.adsdk.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.adsdk.domain.models.adModels.banner.BannerAdData
import com.example.adsdk.presentation.state.BannerAdState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class BannerViewModel: ViewModel() {
    private val _bannerAdState = MutableStateFlow(BannerAdState())
    val bannerAdState: StateFlow<BannerAdState> = _bannerAdState.asStateFlow()

    fun updateState(isLoading: Boolean = false, bannerAdData: BannerAdData? = null, error: String? = null) {
        _bannerAdState.value = BannerAdState(
            isLoading = isLoading,
            bannerAdData = bannerAdData,
            error = error
        )
    }
}