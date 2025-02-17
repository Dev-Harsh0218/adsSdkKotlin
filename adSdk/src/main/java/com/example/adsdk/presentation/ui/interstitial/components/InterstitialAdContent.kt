package com.example.adsdk.presentation.ui.interstitial.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.adsdk.BuildConfig
import com.example.adsdk.domain.models.adModels.interstitial.InterstitialAdData

@Composable
fun InterstitialAdContent(
    adData:InterstitialAdData,
    onAdLoaded:()->Unit,
    onAdClicked:()->Unit,
    onError: (Throwable) -> Unit
){
    val context = LocalContext.current
    AsyncImage(
        model = "http://${BuildConfig.BACKEND_URL}/images/${adData.imageAsset}",
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .clickable
            {
                onAdClicked()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adData.appUrl))
                context.startActivity(intent)
            },
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(Color.Gray),
        onError = { state -> onError(state.result.throwable)},
        onSuccess = { onAdLoaded() }
    )
}


