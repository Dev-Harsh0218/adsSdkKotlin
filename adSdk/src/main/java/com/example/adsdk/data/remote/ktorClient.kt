package com.example.adsdk.data.remote

import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdRequest
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdResponse
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdRequest
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdResponse
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdRequest
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdResponse
import com.example.adsdk.domain.models.appModels.RegisterAppRequest
import com.example.adsdk.domain.models.appModels.RegisterAppResponse
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickResponse
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionRequest
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }

    // POST - for initializing/ registering the app
    suspend fun registerApp(
        baseUrl: String,
        registerAppRequest: RegisterAppRequest
    ): RegisterAppResponse {
        return client.post("http://$baseUrl/api/v1/apps/register-app") {
            contentType(ContentType.Application.Json)
            setBody(registerAppRequest)
        }.body<RegisterAppResponse>()
    }

   // GET - for getting a Random Interstitial Ad
    suspend fun getRandomInterstitialAd(
        baseUrl: String,
        getRandomInterstitialAdRequest: GetRandomInterstitialAdRequest
    ): GetRandomInterstitialAdResponse {
        return client.get("http://$baseUrl/api/v1/run-ads/apkUniqueKey-get-random-interstitial-ad"){
            contentType(ContentType.Application.Json)
            parameter("apk_unique_key",getRandomInterstitialAdRequest.apkUniqueKey)
        }.body<GetRandomInterstitialAdResponse>()
    }

    // GET - for getting a Random Banner Ad
    suspend fun getRandomBannerAd(
        baseUrl: String,
        getRandomBannerAdRequest: GetRandomBannerAdRequest
    ): GetRandomBannerAdResponse {
        return client.get("http://$baseUrl/api/v1/run-ads/apkUniqueKey-get-random-banner-ad") {
            contentType(ContentType.Application.Json)
            parameter("apk_unique_key", getRandomBannerAdRequest.apkUniqueKey)
        }.body<GetRandomBannerAdResponse>()
    }

    // GET - for getting a Random Popup Ad
    suspend fun getRandomPopupAd(
        baseUrl:String,
        getRandomPopupAdRequest: GetRandomPopupAdRequest
    ): GetRandomPopupAdResponse {
        return client.get("http://$baseUrl/api/v1/run-ads/apkUniqueKey-get-random-popup-ad"){
            contentType(ContentType.Application.Json)
            parameter("apk_unique_key",getRandomPopupAdRequest.apkUniqueKey)
        }.body<GetRandomPopupAdResponse>()
    }

    // Put - Increment Impression Ad
    suspend fun incrementAdImpression(
        baseUrl: String,
        incrementAdImpressionRequest: IncrementAdImpressionRequest
    ): IncrementAdImpressionResponse {
        return client.put("http://$baseUrl/api/v1/run-ads/increment-ad-impression"){
            contentType(ContentType.Application.Json)
            setBody(incrementAdImpressionRequest)
        }.body<IncrementAdImpressionResponse>()
    }

    // Put - Increment Ad Click
    suspend fun incrementAdClick(
        baseUrl:String,
        incrementAdClickRequest: IncrementAdClickRequest
    ): IncrementAdClickResponse {
        return client.put("http://$baseUrl/api/v1/run-ads/increment-ad-click"){
            contentType(ContentType.Application.Json)
            setBody(incrementAdClickRequest)
        }.body<IncrementAdClickResponse>()
    }
}

