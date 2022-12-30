plugins {
    id("simplenoteapp.android.library")
    id("simplenoteapp.android.library.compose")
    id("simplenoteapp.spotless")
}

dependencies {
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.constraintlayout)
}
