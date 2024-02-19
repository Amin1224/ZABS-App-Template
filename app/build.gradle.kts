import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.firebase.crashlytics")

}

android {
    namespace = "com.zabs.zabstest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zabs.zabstest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {

            isMinifyEnabled = true
            isShrinkResources = true

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "AdmobAppId", "ca-app-pub-1736986945908339~1372096266")
            resValue("string", "AdmobInterstitialId", "ca-app-pub-1736986945908339/7570673101")
            resValue("string", "AdmobNativeId", "ca-app-pub-1736986945908339/8300520597")
            resValue("string", "AdmobBannerId", "ca-app-pub-1736986945908339/9458469847")
        }
        debug {


            isMinifyEnabled = false
            isShrinkResources = false

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            resValue("string", "AdmobAppId", "ca-app-pub-3940256099942544~3347511713")
            resValue("string", "AdmobInterstitialId", "ca-app-pub-3940256099942544/1033173712")
            resValue("string", "AdmobNativeId", "ca-app-pub-3940256099942544/2247696110")
            resValue("string", "AdmobBannerId", "ca-app-pub-3940256099942544/6300978111")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":zeem"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")


}