package com.example.adsdk.data.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    fun saveAppDetails(
        appId: String,
        appName: String,
        packageName: String,
        appVersion: String,
        appApkKey: String
    ) {
        prefs.edit().apply {
            putString(KEY_APP_ID, appId)
            putString(KEY_APP_NAME, appName)
            putString(KEY_PACKAGE_NAME, packageName)
            putString(KEY_APP_VERSION, appVersion)
            putString(KEY_APP_APK_KEY, appApkKey)
        }.apply()
    }

    fun getAppId(): String? = prefs.getString(KEY_APP_ID, null)
    fun getAppName(): String? = prefs.getString(KEY_APP_NAME, null)
    fun getPackageName(): String? = prefs.getString(KEY_PACKAGE_NAME, null)
    fun getAppVersion(): String? = prefs.getString(KEY_APP_VERSION, null)
    fun getAppApkKey(): String? = prefs.getString(KEY_APP_APK_KEY, null)

    companion object {
        private const val PREFS_NAME = "ad_sdk_prefs"
        private const val KEY_APP_ID = "app_id"
        private const val KEY_APP_NAME = "app_name"
        private const val KEY_PACKAGE_NAME = "package_name"
        private const val KEY_APP_VERSION = "app_version"
        private const val KEY_APP_APK_KEY = "app_apk_key"
    }
}
