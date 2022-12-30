plugins {
    id("simplenoteapp.android.library")
    id("simplenoteapp.spotless")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

dependencies {
    api(libs.moshi)
    ksp(libs.moshi.codegen)
}