// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Define plugin dependencies but don't apply them here, apply them in the app module instead.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}


