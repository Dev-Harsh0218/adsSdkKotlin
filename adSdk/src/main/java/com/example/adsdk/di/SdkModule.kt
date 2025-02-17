package com.example.adsdk.di

import com.example.adsdk.data.di.dataModule
import com.example.adsdk.domain.di.domainModule
import org.koin.dsl.module
val sdkModule = module {
    includes(dataModule, domainModule)
}