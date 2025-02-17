# Public API
-keep class com.example.adsdk.AdsSDKClient { *; }
-keep class com.example.adsdk.presentation.ui.** { *; }
-keepattributes *Annotation*

# Domain Layer
-keep class com.example.adsdk.domain.models.** { *; }
-keep class com.example.adsdk.domain.repository.** { *; }
-keep class com.example.adsdk.domain.usecase.** { *; }

# Data Layer
-keep class com.example.adsdk.data.models.** { *; }
-keep class com.example.adsdk.data.repository.** { *; }

# Koin
-keep class org.koin.** { *; }
-keep class org.koin.java.** { *; }
-keep class org.koin.core.** { *; }
-keep class kotlin.reflect.** { *; }

# Keep Serialization
-keepattributes Signature
