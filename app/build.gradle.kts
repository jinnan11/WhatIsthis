import com.android.build.gradle.internal.api.BaseVariantOutputImpl

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.lsparanoid)
}

android {
    namespace = "what.isThis"
    compileSdk = 34

    defaultConfig {
        applicationId = namespace
        minSdk = 31
        targetSdk = 34
        versionCode = 190
        versionName = "1.9.0"
        //noinspection ChromeOsAbiSupport
        ndk.abiFilters += arrayOf("arm64-v8a")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions",
            "-language-version=2.0",
        )
    }

    packaging {
        resources {
            excludes += "**"
        }
        applicationVariants.all {
            outputs.all {
                (this as BaseVariantOutputImpl).outputFileName = "WhatIsThis-$versionName.apk"
            }
        }
    }
}

lsparanoid {
    classFilter = { true }
    includeDependencies = true
}

dependencies {
    compileOnly(libs.xposed)
    implementation(libs.ezXHelper)
    implementation(libs.dexKit)
}