package com.example.adsdk.internal

import android.content.Context
import android.util.Log
import com.example.adsdk.BuildConfig
import com.example.adsdk.data.util.PreferencesManager
import com.example.adsdk.di.sdkModule
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdRequest
import com.example.adsdk.domain.models.adModels.banner.GetRandomBannerAdResponse
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdRequest
import com.example.adsdk.domain.models.adModels.interstitial.GetRandomInterstitialAdResponse
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdRequest
import com.example.adsdk.domain.models.adModels.popup.GetRandomPopupAdResponse
import com.example.adsdk.domain.models.appModels.RegisterAppRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickRequest
import com.example.adsdk.domain.models.incrementAdClickModels.IncrementAdClickResponse
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionRequest
import com.example.adsdk.domain.models.incrementAdimpressionModels.IncrementAdImpressionResponse
import com.example.adsdk.domain.usecase.GetRandomBannerAdUseCase
import com.example.adsdk.domain.usecase.GetRandomInterstitialAdUseCase
import com.example.adsdk.domain.usecase.GetRandomPopupAdUseCase
import com.example.adsdk.domain.usecase.IncrementAdClickUseCase
import com.example.adsdk.domain.usecase.IncrementAdImpressionUseCase
import com.example.adsdk.domain.usecase.SdkInitializerUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okio.IOException
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject

internal object AdsSdkInitializer {
    private const val TAG = "AdsSdkInitializer"
    private const val BASE_URL = BuildConfig.BACKEND_URL

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception: ${throwable.message}", throwable)
    }
    private val sdkJob = SupervisorJob()
    private val sdkScope = CoroutineScope(Dispatchers.Main + sdkJob + exceptionHandler)
    // Replace KoinJavaComponent.inject with property delegation
//    private val preferencesManager by lazy { get<PreferencesManager>() }
//    private val sdkInitializerUseCase by lazy { get<SdkInitializerUseCase>() }
//    private val getRandomInterstitialAdUseCase by lazy { get<GetRandomInterstitialAdUseCase>() }
//    private val getRandomBannerUseCase by lazy { get<GetRandomBannerAdUseCase>() }
//    private val getRandomPopupUseCase by lazy { get<GetRandomPopupAdUseCase>() }
//    private val incrementAdImpressionUseCase by lazy { get<IncrementAdImpressionUseCase>() }
//    private val incrementAdClickUseCase by lazy { get<IncrementAdClickUseCase>() }
//    private inline fun <reified T> get(): T = getKoin().get()
    private val preferencesManager: PreferencesManager by inject(PreferencesManager::class.java)
    private val sdkInitializerUseCase: SdkInitializerUseCase by inject(SdkInitializerUseCase::class.java)
    private val getRandomInterstitialAdUseCase: GetRandomInterstitialAdUseCase by inject(GetRandomInterstitialAdUseCase::class.java)
    private val getRandomBannerUseCase: GetRandomBannerAdUseCase by inject(GetRandomBannerAdUseCase::class.java)
    private val getRandomPopupUseCase: GetRandomPopupAdUseCase by inject(GetRandomPopupAdUseCase::class.java)
    private val incrementAdImpressionUseCase: IncrementAdImpressionUseCase by inject(IncrementAdImpressionUseCase::class.java)
    private val incrementAdClickUseCase :  IncrementAdClickUseCase by inject(IncrementAdClickUseCase::class.java)
    private var initializationDeferred = CompletableDeferred<Boolean>()
    private var isInitialized = false

    internal fun initialize(
        context: Context,
        appName: String,
        appApkKey: String,
        packageName: String,
        appVersion: String
    ) {
        if (isInitialized) {
            Log.w(TAG, "SDK already initialized")
            return
        }
        sdkScope.launch {
            try {
                startKoin {
                    androidContext(context)
                    modules(sdkModule)
                }
                withTimeout(30_000) {
                    withContext(Dispatchers.IO) {
                        val request = RegisterAppRequest(
                            appName = appName,
                            appApkKey = appApkKey,
                            appPackageName = packageName,
                            appVersion = appVersion
                        )
                        val result = sdkInitializerUseCase(BASE_URL, request)
                        withContext(Dispatchers.Main) {
                            isInitialized = true
                            initializationDeferred.complete(true)
                            Log.d(TAG, "SDK initialized successfully: $result")
                        }
                    }
                }
            } catch (e: Exception) {
                initializationDeferred.complete(false)
                isInitialized = false
                Log.e(TAG, "SDK initialization failed", e)
            }
        }
    }

    internal fun triggerInterstitialAd(
        onSuccess: (GetRandomInterstitialAdResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        sdkScope.launch {
            if (!isInitialized) {
                if (!initializationDeferred.isCompleted) {
                    val isInitSuccess = initializationDeferred.await()
                    if (!isInitSuccess) {
                        onError(IllegalStateException("SDK initialization failed"))
                        return@launch
                    }
                } else {
                    onError(IllegalStateException("SDK not initialized"))
                    return@launch
                }
            }
            if (BASE_URL.isBlank()) {
                onError(IllegalStateException("Invalid BASE_URL"))
                return@launch
            }

            try {
                val appId = preferencesManager.getAppId()?.takeIf { it.isNotBlank() }
                    ?: throw IllegalStateException("Invalid or missing App ID")
                val request = GetRandomInterstitialAdRequest(apkUniqueKey = appId)
                val result = withContext(Dispatchers.IO) {
                    getRandomInterstitialAdUseCase(BASE_URL, request)
                }
                result.fold(
                    onSuccess = { response -> onSuccess(response) },
                    onFailure = { error -> onError(error) }
                )
            } catch (e: Exception) {
                val error = when (e) {
                    is IllegalStateException -> e
                    is IOException -> IllegalStateException("Network error: ${e.message}")
                    else -> IllegalStateException("Unknown error occurred: ${e.message}")
                }
                onError(error)
            }
        }
    }

    internal fun triggerPopupAd(
        onSuccess: (GetRandomPopupAdResponse) -> Unit,
        onError: (Throwable) -> Unit
    ){
        sdkScope.launch{
            if (!isInitialized) {
                if (!initializationDeferred.isCompleted) {
                    val isInitSuccess = initializationDeferred.await()
                    if (!isInitSuccess) {
                        onError(IllegalStateException("SDK initialization failed"))
                        return@launch
                    }
                } else {
                    onError(IllegalStateException("SDK not initialized"))
                    return@launch
                }
            }
            if (BASE_URL.isBlank()) {
                onError(IllegalStateException("Invalid BASE_URL"))
                return@launch
            }

            try{
                val appId = preferencesManager.getAppId()?.takeIf { it.isNotBlank() }
                    ?: throw IllegalStateException("Invalid or missing App ID")
                val request = GetRandomPopupAdRequest(apkUniqueKey = appId)
                val result = withContext(Dispatchers.IO){
                    getRandomPopupUseCase(BASE_URL,request)
                }
                result.fold(
                    onSuccess = { response -> onSuccess(response)},
                    onFailure = { error -> onError(error)}
                )
            } catch(e: Exception){
                val error = when(e){
                    is IllegalStateException -> e
                    is IOException -> IllegalStateException("Network error: ${e.message} ")
                    else -> IllegalStateException("Unknown error occurred: ${e.message}")
                }
                onError(error)
            }
        }
    }

    internal fun triggerBannerAd(
        onSuccess: (GetRandomBannerAdResponse) -> Unit,
        onError: (Throwable) -> Unit
    ){
        sdkScope.launch{
            if(!isInitialized){
               if(!initializationDeferred.isCompleted){
                   val isInitSuccess = initializationDeferred.await()
                   if(!isInitSuccess){
                       onError(IllegalStateException("SDK initialized Failed"))
                       return@launch
                   }
               } else {
                   onError(IllegalStateException("SDK not initialized"))
                   return@launch
               }
            }
            if (BASE_URL.isBlank()) {
                onError(IllegalStateException("Invalid BASE_URL"))
                return@launch
            }

            try{
                val appId = preferencesManager.getAppId()?.takeIf { it.isNotBlank() }
                    ?: throw IllegalStateException("Invalid or missing App ID")
                val request  = GetRandomBannerAdRequest(apkUniqueKey = appId)
                val result = withContext(Dispatchers.IO){
                    getRandomBannerUseCase(BASE_URL,request)
                }
                result.fold(
                    onSuccess = { response -> onSuccess(response)},
                    onFailure = { error -> onError(error)}
                )
            } catch (e : Exception){
                val error = when(e){
                    is IllegalStateException -> e
                    is IOException -> IllegalStateException("Network error: ${e.message}")
                    else -> IllegalStateException("Unknown error occurred ${e.message}")
                }
                onError(error)
            }
        }
    }

    internal fun incrementAdImpression(
        adId:String,
        onSuccess: (IncrementAdImpressionResponse) -> Unit,
        onError: (Throwable) -> Unit
    ){
        sdkScope.launch{
            if(!isInitialized){
                if(!initializationDeferred.isCompleted){
                    val isInitSuccess = initializationDeferred.await()
                    if(!isInitSuccess){
                        onError(IllegalStateException("SDK initialized Failed"))
                        return@launch
                    }
                } else {
                    onError(IllegalStateException("SDK not initialized"))
                    return@launch
                }
            }
            if (BASE_URL.isBlank()) {
                onError(IllegalStateException("Invalid BASE_URL"))
                return@launch
            }
            if (adId.isEmpty()){
                onError(IllegalStateException("adId cannot be empty or null"))
                return@launch
            }
            try{
                val request = IncrementAdImpressionRequest(runningAdId = adId)
                val result = withContext(Dispatchers.IO){
                    incrementAdImpressionUseCase(BASE_URL,request)
                }
                result.fold(
                    onSuccess = { response -> onSuccess(response)},
                    onFailure = { error -> onError(error)}
                )
            } catch (e: Exception){
                val error = when(e){
                    is IllegalStateException -> e
                    else -> IllegalStateException("Unknown Error occurred ${e.message}")
                }
                onError(error)
            }

        }
    }

    internal fun incrementAdClick(
        adId: String,
        onSuccess: (IncrementAdClickResponse) -> Unit,
        onError: (Throwable) -> Unit
    ){
       sdkScope.launch {
           if(!isInitialized){
               if(!initializationDeferred.isCompleted){
                   val isInitSuccess = initializationDeferred.await()
                   if(!isInitSuccess){
                       onError(IllegalStateException("SDK initialized Failed"))
                       return@launch
                   }
               } else {
                   onError(IllegalStateException("SDK not initialized"))
                   return@launch
               }
           }
           if (BASE_URL.isBlank()) {
               onError(IllegalStateException("Invalid BASE_URL"))
               return@launch
           }
           if (adId.isEmpty()){
               onError(IllegalStateException("adId cannot be empty or null"))
               return@launch
           }
           try{
               val request = IncrementAdClickRequest(runningAdId = adId)
               val result = withContext(Dispatchers.IO){
                   incrementAdClickUseCase(BASE_URL,request)
               }
               result.fold(
                   onSuccess = { response -> onSuccess(response)},
                   onFailure = { error -> onError(error)}
               )
           } catch(e: Exception){
               val error = when(e){
                   is IllegalStateException -> e
                   else -> IllegalStateException("Unknown Error occurred ${e.message}")
               }
               onError(error)
           }
       }
    }

    internal fun destroy() {
        sdkJob.cancel()
        stopKoin()
        isInitialized = false
        initializationDeferred = CompletableDeferred()
    }
}