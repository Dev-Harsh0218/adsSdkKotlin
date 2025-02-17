package com.example.adsdk.presentation.ui.banner.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.adsdk.BuildConfig
import com.example.adsdk.domain.models.adModels.banner.BannerAdData
import androidx.compose.ui.platform.LocalContext

@Composable
fun BannerAdContent(
    adData:BannerAdData,
    onAdLoaded:() -> Unit,
    onAdClicked:() -> Unit,
    onError: (Throwable) -> Unit
){
    val context = LocalContext.current
    AsyncImage(
        model = "http://${BuildConfig.BACKEND_URL}/images/${adData.imageAsset}",
        contentDescription =null,
        modifier = Modifier
            .fillMaxSize()
            .clickable
            {
                onAdClicked()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adData.appUrl))
                context.startActivity(intent)
            },
        contentScale = ContentScale.Crop,
        onSuccess = { onAdLoaded()},
        onError = { state -> onError(state.result.throwable)}

    )
}