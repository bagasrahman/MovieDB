package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.DaggerHilt(){
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
}