import com.android.build.gradle.internal.utils.KSP_PLUGIN_ID

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.homework13"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.homework13"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        dataBinding = true
    }
}

dependencies {
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-android:2.8.4")
    implementation("androidx.fragment:fragment-ktx:1.8.2")
    implementation("io.coil-kt:coil:2.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Moshi
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.4.0")
    ksp("androidx.room:room-compiler:2.6.1")
}