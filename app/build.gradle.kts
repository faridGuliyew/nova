plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.novacommerce"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.novacommerce"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Firebase
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")

    //Navigation
    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    //Hilt
    implementation("androidx.hilt:hilt-compiler:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")

    //Lottie
    implementation("com.airbnb.android:lottie:6.1.0")

    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    androidTestImplementation("androidx.room:room-testing:2.5.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")

    //Encrypted sp
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    //Motion Toast
    implementation("com.github.Spikeysanju:MotionToast:1.4")

    //Dots indicator
    implementation("com.tbuonomo:dotsindicator:5.0")
}