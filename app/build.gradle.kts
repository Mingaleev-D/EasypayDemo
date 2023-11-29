plugins {
   id("com.android.application")
   id("org.jetbrains.kotlin.android")
   id("com.google.devtools.ksp")
   id("kotlin-parcelize")
   id("androidx.navigation.safeargs")
   id("com.google.dagger.hilt.android")
}
kotlin {
   jvmToolchain(18)
}

android {
   namespace = "com.example.easypaydemo"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.example.easypaydemo"
      minSdk = 23
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
   kotlinOptions {
      jvmTarget = "1.8"
   }
   buildFeatures {
      viewBinding = true
   }
}

dependencies {

   implementation("androidx.core:core-ktx:1.12.0")
   implementation("androidx.appcompat:appcompat:1.6.1")
   implementation("com.google.android.material:material:1.10.0")
   implementation("androidx.constraintlayout:constraintlayout:2.1.4")
   implementation("androidx.legacy:legacy-support-v4:1.0.0")

   testImplementation("junit:junit:4.13.2")

   androidTestImplementation("androidx.test.ext:junit:1.1.5")
   androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

   val lifecycleVersion = "2.6.2"
   // ViewModel
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
   // LiveData
   implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
   // Lifecycles only (without ViewModel or LiveData)
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
   // Saved state module for ViewModel
   implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")

   val activityKtx = "1.8.0"
   implementation("androidx.activity:activity-ktx:$activityKtx")
   val fragmentKtx = "1.6.2"
   implementation("androidx.fragment:fragment-ktx:$fragmentKtx")

   // Navigation Components
   val navVersion = "2.7.5"
   implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
   implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
   // Retrofit
   val retrofitVersion = "2.9.0"
   implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
   implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
   // OkHTTP
   val okhttpVersion = "4.11.0"
   implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
   implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
   // Coroutines
   val coroutinesVersion = "1.7.3"
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
   // Hilt
   val hiltVersion = "2.48"
   implementation("com.google.dagger:hilt-android:$hiltVersion")
   ksp("com.google.dagger:hilt-compiler:$hiltVersion")


}