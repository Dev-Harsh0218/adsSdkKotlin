package com.example.adsdk.presentation.ui.banner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.adsdk.domain.models.adModels.banner.BannerAdData
import com.example.adsdk.presentation.ui.banner.components.BannerAdContent

@Composable
fun BannerAdScreen(
    adData: BannerAdData?,
    modifier: Modifier = Modifier,
    onAdLoaded: () -> Unit,
    onAdClicked: () -> Unit,
    onError: (Throwable) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        adData?.let { ad ->
            BannerAdContent(
                adData = ad,
                onAdLoaded = onAdLoaded,
                onAdClicked = onAdClicked,
                onError = onError,
            )
        }
    }
}
