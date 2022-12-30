plugins {
    id("simplenoteapp.android.application")
    id("simplenoteapp.android.application.compose")
    id("simplenoteapp.android.hilt")
    id("simplenoteapp.spotless")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mmd.simplenoteapp"
    compileSdk = Configurations.compileSdk

    defaultConfig {
        applicationId = "com.mmd.simplenoteapp"
        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk
        versionCode = Configurations.versionCode
        versionName = Configurations.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // core modules
    implementation(project(":core-designsystem"))
    implementation(project(":core-navigation"))

    // material
    implementation(libs.androidx.appcompat)

    // compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.material)

    // jetpack
    implementation(libs.androidx.startup)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // image loading
    implementation(libs.bumptech.glide)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.crashlytics)
}
