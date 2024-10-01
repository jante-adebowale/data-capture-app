plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)

    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.janteadebowale.datacapture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.janteadebowale.datacapture"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //View model
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // Crypto
    implementation(libs.androidx.security.crypto.ktx)

    //Splash Api
    implementation (libs.androidx.core.splashscreen)

    // Room
    implementation (libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Navigation
    implementation (libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Extended Icons
    implementation(libs.androidx.material.icons.extended)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Datastore
    implementation (libs.androidx.datastore.preferences)
    implementation(libs.rebugger)

    //Timber
    implementation (libs.timber)

    //Security Crypto
    implementation(libs.androidx.security.crypto.ktx)

    //Work Manager
    implementation(libs.work.manager)

    //Koin
    implementation(libs.bundles.koin)
    implementation(libs.bundles.koin.compose)

    //Permission
    implementation( libs.accompanist.permissions)

    //Material
    implementation(libs.google.android.material)

}