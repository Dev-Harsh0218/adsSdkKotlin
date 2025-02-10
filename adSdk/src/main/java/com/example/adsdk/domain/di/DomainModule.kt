package com.example.adsdk.domain.di

import com.example.adsdk.domain.usecase.SdkInitializerUseCase
import org.koin.dsl.module

val domainModule = module {
    // Use cases
    factory { SdkInitializerUseCase(get()) }
//    factory { GetBannerAdUseCase(get()) }
//    factory { GetInterstitialAdUseCase(get()) }
//    factory { GetPopupAdUseCase(get()) }

    // Track ad interactions
//    factory { TrackAdImpressionUseCase(get()) }
//    factory { TrackAdClickUseCase(get()) }
}