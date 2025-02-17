package com.example.adsdk.data.di

import com.example.adsdk.data.remote.KtorClient
import com.example.adsdk.data.repository.GetRandomBannerAdImpl
import com.example.adsdk.data.repository.GetRandomInterstitialAdImpl
import com.example.adsdk.data.repository.GetRandomPopupAdImpl
import com.example.adsdk.data.repository.IncrementAdClickImpl
import com.example.adsdk.data.repository.IncrementAdImpressionImpl
import com.example.adsdk.data.repository.SdkInitializerRepositoryImpl
import com.example.adsdk.data.util.PreferencesManager
import com.example.adsdk.domain.repository.GetRandomBannerAdRepository
import com.example.adsdk.domain.repository.GetRandomInterstitialAdRepository
import com.example.adsdk.domain.repository.GetRandomPopupAdRepository
import com.example.adsdk.domain.repository.IncrementAdClickRepository
import com.example.adsdk.domain.repository.IncrementAdImpressionRepository
import com.example.adsdk.domain.repository.SdkInitializerRepository
import org.koin.dsl.module

val dataModule = module {
    single { KtorClient() }
    single { PreferencesManager(get()) }
    single<SdkInitializerRepository> { SdkInitializerRepositoryImpl(get(), get()) }
    single<GetRandomInterstitialAdRepository> { GetRandomInterstitialAdImpl(get()) }
    single<GetRandomBannerAdRepository> { GetRandomBannerAdImpl(get()) }
    single<GetRandomPopupAdRepository> { GetRandomPopupAdImpl(get()) }
    single<IncrementAdClickRepository> { IncrementAdClickImpl(get()) }
    single<IncrementAdImpressionRepository> { IncrementAdImpressionImpl(get()) }
}



