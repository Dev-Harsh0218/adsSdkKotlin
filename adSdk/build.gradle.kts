// adSdk Build.gradle.kts
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
    `maven-publish`
}

android {
    namespace = "com.example.adsdk"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        buildConfigField(
            "String",
            "BACKEND_URL",
            value = "\"${findProperty("BACKEND_URL") ?: "192.168.0.81:8000"}\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false// Enable minification
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    publishing{
        singleVariant("release"){
            withSourcesJar()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    //koin
    api(libs.koin.core)
    api(libs.koin.android)
//    api(libs.koin.java)
    //ktor
    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.core.android)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation.layout.android)
    //internal prev dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}