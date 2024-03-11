plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.tmdbclone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tmdbclone"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

//    signingConfigs {
//        release {
//            storeFile =
//                file("\"C:\\Users\\l.mazmishvili\\Documents\\KeyStores\\TMDB_key_store.jks\"")
//            storePassword = "asdlasd123"
//            keyAlias = "TMDB_Key"
//            keyPassword = "asdlasd123"
//        }
//    }

    buildTypes {
        release {
//            signingConfig = signingConfigs.release
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Dagger-Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Retrofit and Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Lifecycle ktx
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Swipe Refresh Layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Data Store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    // View Pager 2
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta02")

}