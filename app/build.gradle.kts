
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt) // Apply Hilt plugin
}

android {
    namespace = "com.androidchallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.androidchallenge"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

}

dependencies {
    // Core AndroidX Libraries
    implementation ("androidx.core:core:1.9.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    // Hilt Dependency Injection
    implementation ("com.google.dagger:hilt-android:2.44")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:2.44")

    // Retrofit for Networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // OkHttp for HTTP Client & Logging
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // RxJava & RxAndroid
    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")

    // RxLifecycle for Managing Rx Observables
    implementation ("com.trello.rxlifecycle4:rxlifecycle:4.0.2")
    implementation ("com.trello.rxlifecycle4:rxlifecycle-android:4.0.2")
    implementation ("com.trello.rxlifecycle4:rxlifecycle-components:4.0.2")

    // Glide for Image Loading
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    // ViewModel & LiveData (Java Support)
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.6.1")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.1")

    // Unit Testing
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
}
