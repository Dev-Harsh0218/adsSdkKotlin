package com.example.adsnativesdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adsdk.AdsSDKClient
import com.example.adsnativesdk.ui.theme.AdsNativeSdkTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AdsSDKClient.initialize(
            context = applicationContext,
            appName = "NativeTesting",
            appApkKey = "nativeTest",
            packageName = "com.native.test",
            appVersion = "0.0.1"
        )

        AdsSDKClient.triggerBannerAd("main_screen")

        setContent{
            AdsNativeSdkTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(contentAlignment = Alignment.Center){
                        Button(onClick = {AdsSDKClient.triggerInterstitialAd(containerId = "main_screen_interstitial")}) {
                            Text(text = "Show Interstitial Ad")
                        }
                    }
                    Box(contentAlignment = Alignment.Center){
                        Button(onClick = {AdsSDKClient.triggerPopupAd(containerId = "main_screen_popup")}) {
                            Text(text = "Show PopUp Ad")
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    AdsSDKClient.BannerAdContainer(
                        containerId = "main_screen",
                        content = {},
                        height = 60.dp
                    )
                }
                AdsSDKClient.InterstitialAdContainer(containerId = "main_screen_interstitial", content = {})
                AdsSDKClient.PopupAdContainer(containerId = "main_screen_popup", content = {})
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdsNativeSdkTheme {
        Greeting("Android")
    }
}
