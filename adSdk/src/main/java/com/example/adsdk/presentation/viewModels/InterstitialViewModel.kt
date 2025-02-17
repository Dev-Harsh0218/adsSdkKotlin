package com.example.adsdk.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.adsdk.domain.models.adModels.interstitial.InterstitialAdData
import com.example.adsdk.presentation.state.InterstitialAdState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InterstitialViewModel : ViewModel() {
    private val _interstitialAdState = MutableStateFlow(InterstitialAdState())
    val interstitialAdState: StateFlow<InterstitialAdState> = _interstitialAdState.asStateFlow()

    fun updateState(isLoading: Boolean = false, interstitialAdData: InterstitialAdData? = null, error: String? = null) {
        _interstitialAdState.value = InterstitialAdState(
            isLoading = isLoading,
            interstitialAdData = interstitialAdData,
            error = error
        )
    }
}
