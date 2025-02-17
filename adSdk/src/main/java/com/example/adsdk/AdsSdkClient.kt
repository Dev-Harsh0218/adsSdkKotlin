package com.example.adsdk

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.adsdk.internal.AdsSdkInitializer
import com.example.adsdk.presentation.ui.banner.BannerAdScreen
import com.example.adsdk.presentation.ui.interstitial.InterstitialAdScreen
import com.example.adsdk.presentation.ui.popup.PopupAdScreen
import com.example.adsdk.presentation.viewModels.BannerViewModel
import com.example.adsdk.presentation.viewModels.InterstitialViewModel
import com.example.adsdk.presentation.viewModels.PopupViewModel
import kotlinx.coroutines.launch
object AdsSDKClient {
    private val activeContainerIds = mutableSetOf<String>()
    private val bannerViewModels = mutableMapOf<String, BannerViewModel>()
    private val interstitialViewModels = mutableMapOf<String, InterstitialViewModel>()
    private val popupViewModels = mutableMapOf<String, PopupViewModel>()

    fun initialize(
        context: Context,
        appName: String,
        appApkKey: String,
        packageName: String,
        appVersion: String
    ) {
        AdsSdkInitializer.initialize(
            context = context,
            appName = appName,
            appApkKey = appApkKey,
            packageName = packageName,
            appVersion = appVersion
        )
    }

    @Composable
    private fun LoadingIndicator(size: Dp = 50.dp) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Black,
                modifier = Modifier.size(size)
            )
        }
    }

    @Composable
    fun BannerAdContainer(
        containerId: String,
        content: @Composable () -> Unit,
        width: Dp = Dp.Unspecified,
        height: Dp = 50.dp,
        loaderSize: Dp = 20.dp
    ) {
        DisposableEffect(containerId) {
            activeContainerIds.add(containerId)
            onDispose {
                activeContainerIds.remove(containerId)
                bannerViewModels.remove(containerId)
            }
        }

        val bannerViewModel = bannerViewModels.getOrPut(containerId) { BannerViewModel() }
        Box(
            modifier = Modifier
                .then(
                    if (width == Dp.Unspecified)
                        Modifier.fillMaxWidth()
                    else
                        Modifier.width(width)
                )
                .height(height)
        ) {
            content()
            val bannerAdState by bannerViewModel.bannerAdState.collectAsStateWithLifecycle()
            when {
                bannerAdState.isLoading -> LoadingIndicator(loaderSize)
            }
            bannerAdState.bannerAdData?.let { bannerAdData ->
                BannerAdScreen(
                    adData = bannerAdData,
                    onAdLoaded = {
                        AdsSdkInitializer.incrementAdImpression(
                            adId = bannerAdData.adId,
                            onSuccess = { Log.d("AdsSDKClient", "Ad Impression incremented successfully") },
                            onError = { Log.d("AdsSDKClient", "Ad Impression incrementation failed", it) }
                        )
                    },
                    onAdClicked = {
                        AdsSdkInitializer.incrementAdClick(
                            adId = bannerAdData.adId,
                            onSuccess = { Log.d("AdsSDKClient", "Ad Click incremented successfully") },
                            onError = { Log.d("AdsSDKClient", "Ad Click incrementation failed", it) }
                        )
                    },
                    onError = {},
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun InterstitialAdContainer(
        containerId: String,
        loaderSize: Dp = 50.dp,
        content: @Composable () -> Unit
    ) {
        DisposableEffect(containerId) {
            activeContainerIds.add(containerId)
            onDispose {
                activeContainerIds.remove(containerId)
                interstitialViewModels.remove(containerId)
            }
        }

        val interstitialViewModel = interstitialViewModels.getOrPut(containerId) { InterstitialViewModel() }
        Box(modifier = Modifier.fillMaxSize()) {
            content()
            val interstitialState by interstitialViewModel.interstitialAdState.collectAsStateWithLifecycle()
            when {
                interstitialState.isLoading -> LoadingIndicator(loaderSize)
            }
            interstitialState.interstitialAdData?.let { interstitialAdData ->
                InterstitialAdScreen(
                    onAdLoaded = {
                        AdsSdkInitializer.incrementAdImpression(
                            adId = interstitialAdData.adId,
                            onSuccess = { Log.d("AdsSDKClient", "Ad Impression incremented successfully") },
                            onError = { Log.e("AdsSDKClient", "Ad Impression incrementation failed", it) }
                        )
                    },
                    onAdClicked = {
                        AdsSdkInitializer.incrementAdClick(
                            adId = interstitialAdData.adId,
                            onSuccess = { Log.d("AdsSDKClient", "Ad Click incremented successfully") },
                            onError = { Log.d("AdsSDKClient", "Ad Click incrementation failed", it) }
                        )
                    },
                    onDismiss = { interstitialViewModel.updateState(interstitialAdData = null) },
                    onError = {},
                    adData = interstitialAdData
                )
            }
        }
    }

    @Composable
    fun PopupAdContainer(
        containerId: String,
        loaderSize: Dp = 50.dp,
        content: @Composable () -> Unit,
        popupWidth: Dp = 300.dp,
        popupHeight: Dp = 400.dp,
        popupAlignment: Alignment = Alignment.Center
    ) {
        DisposableEffect(containerId) {
            activeContainerIds.add(containerId)
            onDispose {
                activeContainerIds.remove(containerId)
                popupViewModels.remove(containerId)
            }
        }

        val popupViewModel = popupViewModels.getOrPut(containerId) { PopupViewModel() }
        Box(modifier = Modifier.fillMaxSize()) {
            content()
            val popupState by popupViewModel.popupAdState.collectAsStateWithLifecycle()
            when {
                popupState.isLoading -> LoadingIndicator(loaderSize)
            }
            popupState.popupAdData?.let { popupAdData ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                )
                Box(
                    modifier = Modifier
                        .width(popupWidth)
                        .height(popupHeight)
                        .align(popupAlignment)
                ) {
                    PopupAdScreen(
                        adData = popupAdData,
                        onAdLoaded = {
                            AdsSdkInitializer.incrementAdImpression(
                                adId = popupAdData.adId,
                                onSuccess = { Log.d("AdsSDKClient", "Ad Impression incremented successfully") },
                                onError = { Log.e("AdsSDKClient", "Ad Impression incrementation failed", it) }
                            )
                        },
                        onAdClicked = {
                            AdsSdkInitializer.incrementAdClick(
                                adId = popupAdData.adId,
                                onSuccess = { Log.d("AdsSDKClient", "Ad Click incremented successfully") },
                                onError = { Log.d("AdsSDKClient", "Ad Click incrementation failed", it) }
                            )
                        },
                        onDismiss = { popupViewModel.updateState(popupAdData = null) },
                        onError = {}
                    )
                }
            }
        }
    }

    @JvmStatic
    fun triggerBannerAd(containerId: String) {
        val viewModel = bannerViewModels.getOrPut(containerId) { BannerViewModel() }
        viewModel.updateState(isLoading = true)

        viewModel.viewModelScope.launch {
            AdsSdkInitializer.triggerBannerAd(
                onSuccess = { response -> viewModel.updateState(bannerAdData = response.data) },
                onError = { error ->
                    viewModel.updateState(error = error.message)
                    Log.e("AdsSDKClient", "Banner Load Failed", error)
                }
            )
        }
    }

    @JvmStatic
    fun triggerInterstitialAd(containerId: String) {
        val viewModel = interstitialViewModels.getOrPut(containerId) { InterstitialViewModel() }
        viewModel.updateState(isLoading = true)

        viewModel.viewModelScope.launch {
            AdsSdkInitializer.triggerInterstitialAd(
                onSuccess = { response -> viewModel.updateState(interstitialAdData = response.data) },
                onError = { error ->
                    viewModel.updateState(error = error.message)
                    Log.e("AdsSDKClient", "Interstitial Load Failed", error)
                }
            )
        }
    }

    @JvmStatic
    fun triggerPopupAd(containerId: String) {
        val viewModel = popupViewModels.getOrPut(containerId) { PopupViewModel() }
        viewModel.updateState(isLoading = true)

        viewModel.viewModelScope.launch {
            AdsSdkInitializer.triggerPopupAd(
                onSuccess = { response -> viewModel.updateState(popupAdData = response.data) },
                onError = { error ->
                    viewModel.updateState(error = error.message)
                    Log.e("AdsSDKClient", "Popup Load Failed", error)
                }
            )
        }
    }
}