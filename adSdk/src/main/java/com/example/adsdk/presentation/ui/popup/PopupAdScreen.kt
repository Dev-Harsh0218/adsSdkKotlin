package com.example.adsdk.presentation.ui.popup

import androidx.compose.foundation.background
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
import com.example.adsdk.domain.models.adModels.popup.PopupAdData
import com.example.adsdk.presentation.ui.popup.components.PopupAdContent

@Composable
fun PopupAdScreen(
    adData: PopupAdData?,
    onAdLoaded:() -> Unit,
    onAdClicked:() -> Unit,
    onDismiss: () -> Unit,
    onError: (Throwable) -> Unit,
) {
   Box(
       modifier = Modifier.fillMaxSize()
   ) {
       adData?.let { ad ->
           PopupAdContent(
               adData = ad,
               onAdLoaded = onAdLoaded,
               onAdClicked = onAdClicked,
               onError = onError
           )
       }

       IconButton(
           onClick = { onDismiss() },
           modifier = Modifier
               .align(Alignment.TopEnd)
               .padding(4.dp)
       ) {
           Icon(
               imageVector = Icons.Default.Close,
               contentDescription = "Close Ad",
               tint = Color.White,
           )
       }
   }
}