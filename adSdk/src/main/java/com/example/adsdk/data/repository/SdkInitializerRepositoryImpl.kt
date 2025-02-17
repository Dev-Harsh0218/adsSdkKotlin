package com.example.adsdk.data.repository

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.data.util.PreferencesManager
import com.example.adsdk.domain.models.appModels.RegisterAppRequest
import com.example.adsdk.domain.models.appModels.RegisterAppResponse
import com.example.adsdk.domain.repository.SdkInitializerRepository
class SdkInitializerRepositoryImpl(
    private val ktorClient: KtorClient,
    private val preferencesManager: PreferencesManager
) : SdkInitializerRepository {
    override suspend fun registerApp(
        baseUrl: String,
        registerAppRequest: RegisterAppRequest
    ): Result<RegisterAppResponse> {
        return runCatching {
            val response = ktorClient.registerApp(baseUrl, registerAppRequest)
            response.data.data?.let { appInfo ->
                preferencesManager.saveAppDetails(
                    appId = appInfo.appId,
                    appName = appInfo.appName,
                    packageName = appInfo.packageName,
                    appVersion = appInfo.appVersion,
                    appApkKey = appInfo.appApkKey
                )
            }
            response
        }
    }
}