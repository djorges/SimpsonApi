plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.simpsonapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simpsonapi"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.1")
    implementation("androidx.navigation:navigation-ui:2.7.1")
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.preference:preference:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Fix Duplicate class
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    //ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")

    //Room
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-rxjava3:$room_version")// optional - RxJava3 support for Room

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    //RxJava
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    annotationProcessor("com.google.dagger:hilt-compiler:2.47")
}