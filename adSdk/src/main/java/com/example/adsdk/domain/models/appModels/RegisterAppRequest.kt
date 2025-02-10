package com.example.adsdk.domain.models.appModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterAppRequest(
    @SerialName("app_name") val appName: String,
    @SerialName("app_apk_key") val appApkKey: String,
    @SerialName("app_package_name") val appPackageName: String,
    @SerialName("app_version") val appVersion: String,
    @SerialName("sdk_type") val sdkType: String = "native"
)