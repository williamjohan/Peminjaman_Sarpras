plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.peminjaman_sarpras"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.peminjaman_sarpras"
        minSdk = 24
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
    implementation ("androidx.compose.material3:material3:1.2.1");
    implementation ("androidx.compose.material3:material3-window-size-class:1.2.1");
    implementation ("androidx.compose.material3:material3-adaptive-navigation-suite:1.0.0-alpha07");
    implementation ("com.google.android.material:material:1.12.0");
    implementation ("androidx.navigation:navigation-fragment:2.7.7");
    implementation ("androidx.navigation:navigation-ui:2.7.7");
    implementation ("androidx.lifecycle:lifecycle-livedata:2.8.0");
    implementation ("androidx.activity:activity:1.3.1");
    implementation ("androidx.fragment:fragment:1.3.6");



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.gridlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}