package com.example.adsdk.presentation.ui.interstitial

import com.example.adsdk.domain.models.adModels.interstitial.InterstitialAdData
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.adsdk.presentation.ui.interstitial.components.InterstitialAdContent

@Composable
fun InterstitialAdScreen(
    onAdLoaded: () -> Unit,
    onDismiss: () -> Unit,
    onAdClicked: () -> Unit,
    onError:(Throwable) -> Unit,
    adData:InterstitialAdData?
) {

    Box(modifier = Modifier.fillMaxSize()) {
        adData?.let { adData ->
            InterstitialAdContent(
                adData = adData,
                onAdLoaded = onAdLoaded,
                onAdClicked = onAdClicked,
                onError = onError
            )
        }

        IconButton(
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Ad",
                tint = Color.White
            )
        }
    }
}