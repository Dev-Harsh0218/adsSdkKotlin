# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep SDK public interface
-keep class com.example.adsdk.AdsSDKClient { *; }

# Keep Domain Layer
-keep class com.example.adsdk.domain.models.** { *; }
-keep class com.example.adsdk.domain.repository.** { *; }
-keep class com.example.adsdk.domain.usecase.** { *; }

# Keep Data Layer
-keep class com.example.adsdk.data.models.** { *; }
-keep class com.example.adsdk.data.repository.** { *; }

# Keep Presentation Layer
-keep class com.example.adsdk.presentation.ui.** { *; }
-keep class com.example.adsdk.presentation.viewModels.** { *; }
-keep class com.example.adsdk.presentation.states.** { *; }

# Keep Internal Components
-keep class com.example.adsdk.internal.** { *; }

# Keep Serialization
-keepattributes *Annotation*
-keepattributes Signature

# Keep Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineDispatcher {}

# Keep Koin
-keep class org.koin.** { *; }
-keep class org.koin.java.** { *; }
-keep class org.koin.core.** { *; }
-keep class kotlin.reflect.** { *; }

# Keep Ktor
-keep class io.ktor.** { *; }
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep Coil
-keep class coil.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }


# Keep StringConcatFactory
#-keep class java.lang.invoke.StringConcatFactory { *; }

# Keep Kotlin Metadata
-keep class kotlin.Metadata { *; }
-keepattributes RuntimeVisibleAnnotations
-keepattributes AnnotationDefault

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile