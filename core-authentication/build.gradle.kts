plugins {
    id("simplenoteapp.android.library")
    id("simplenoteapp.android.library.compose")
    id("simplenoteapp.android.hilt")
    id("simplenoteapp.spotless")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    api(libs.androidx.navigation.compose)
}