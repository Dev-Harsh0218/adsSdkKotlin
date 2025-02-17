package com.example.adsdk.domain.di

import com.example.adsdk.domain.usecase.GetRandomBannerAdUseCase
import com.example.adsdk.domain.usecase.GetRandomInterstitialAdUseCase
import com.example.adsdk.domain.usecase.GetRandomPopupAdUseCase
import com.example.adsdk.domain.usecase.IncrementAdClickUseCase
import com.example.adsdk.domain.usecase.IncrementAdImpressionUseCase
import com.example.adsdk.domain.usecase.SdkInitializerUseCase
import org.koin.dsl.module

val domainModule = module {
    // Use cases
    factory { SdkInitializerUseCase(get()) }
    factory { GetRandomInterstitialAdUseCase(get()) }
    factory { GetRandomBannerAdUseCase(get()) }
    factory { GetRandomPopupAdUseCase(get()) }
    // Track ad interactions
    factory { IncrementAdImpressionUseCase(get()) }
    factory { IncrementAdClickUseCase(get()) }
}
