package com.example.adsdk.domain.models.appModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterAppResponse(
    val success: Boolean,
    val message: String,
    val data: AppDetails
)

@Serializable
data class AppDetails(
    val existing: Boolean? = null,
    val data: AppInfo? = null,
    @SerialName("app_id") val appId: String? = null,
    @SerialName("app_name") val appName: String? = null,
    @SerialName("app_package_name") val packageName: String? = null,
    @SerialName("app_version") val appVersion: String? = null,
    @SerialName("app_apk_key") val appApkKey: String? = null
) {
    @Serializable
    data class AppInfo(
        @SerialName("app_id") val appId: String,
        @SerialName("app_name") val appName: String,
        @SerialName("app_package_name") val packageName: String,
        @SerialName("app_version") val appVersion: String,
        @SerialName("app_apk_key") val appApkKey: String
    )
}