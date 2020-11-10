package com.asadmansoor.crumbs.buildsrc

object Build {
    const val compileSdkVersion = 29
    const val minSdkVersion = 15
    const val targetSdkVersion = 29
}

object Libs {
    object Core {
        const val androidGradlePlugin = "com.android.tools.build:gradle:3.6.3"
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

    object Kotlin {
        private const val version = "1.3.71"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Test {
        const val junit = "junit:junit:4.12"
    }

    object AndroidTest {
        const val junit = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}
