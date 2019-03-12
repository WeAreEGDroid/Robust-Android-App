package dependencies

/**
 * Created at Tito on 3/13/19
 *
 * Dependencies that will be used in build.gradle file for each module.
 */

@Suppress("unused")
object Dependencies {

    const val AppCompact =
        "androidx.appcompat:appcompat:${Versions.AndroidX.main}"
    const val AndroidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val AndroidXCore =
        "androidx.core:core:${Versions.AndroidX.main}"

    val AndroidXLibs = arrayOf(
        // Android Annotation
        "androidx.annotation:annotation:${Versions.AndroidX.main}",

        //CardView
        "androidx.cardview:cardview:${Versions.AndroidX.main}",

        // RecyclerView
        "androidx.recyclerview:recyclerview:${Versions.AndroidX.main}",

        // MultiDex
        "androidx.multidex:multidex:${Versions.AndroidX.multiDex}",

        // ConstraintLayout
        "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}",

        // Android Material
        "com.google.android.material:material:${Versions.AndroidX.material}"
    )

    val AndroidArchComponent = arrayOf(
        "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.androidArc}",
        "androidx.lifecycle:lifecycle-reactivestreams:${Versions.AndroidX.androidArc}"
    )

    const val LifeCycleAnnotations =
        "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidX.androidArc}"

    val Testing = arrayOf(
        // Android Unit Testing
        "androidx.test:runner:${Versions.Testing.runner}",
        "androidx.test.espresso:espresso-core:${Versions.Testing.espresso}",

        // Mockito
        "org.mockito:mockito-core:${Versions.Testing.mockito}",

        // JUnit
        "junit:junit:${Versions.Testing.junit}"
    )

    const val KotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.std}"
    const val KotlinPlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.std}"

    val Dagger = arrayOf(
        "com.google.dagger:dagger:${Versions.Google.dagger}",
        "com.google.dagger:dagger-android-support:${Versions.Google.dagger}"
    )
    const val DaggerKapt =
        "com.google.dagger:dagger-compiler:${Versions.Google.dagger}"

    val RxJava = arrayOf(
        "io.reactivex.rxjava2:rxandroid:${Versions.RX.rxAndroid}",
        "io.reactivex.rxjava2:rxjava:${Versions.RX.rxJava}",
        "com.jakewharton.rxrelay2:rxrelay:${Versions.RX.rxRelay}"
    )

    val Retrofit = arrayOf(
        "com.squareup.okhttp3:okhttp:${Versions.Retrofit.okHttp}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Retrofit.okHttp}",
        "com.squareup.okhttp3:okhttp-urlconnection:${Versions.Retrofit.okHttp}",
        "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit}",
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit.retrofit}"
    )
    const val RetrofitTesting =
        "com.squareup.retrofit2:retrofit-mock:${Versions.Retrofit.retrofit}"

    const val OkLog = "com.github.simonpercic:oklog3:${Versions.OK_LOG}"

    val Moshi = arrayOf(
        "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit.retrofit}",
        "com.squareup.moshi:moshi:${Versions.MOSHI}"
    )

    val LeakCanary = arrayOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    )

    val Glide = arrayOf(
        "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    )
    const val GlideKapt = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    val Room = arrayOf(
        "androidx.room:room-runtime:${Versions.AndroidX.androidArc}",
        "androidx.room:room-rxjava2:${Versions.AndroidX.androidArc}"
    )
    const val RoomKapt =
        "androidx.room:room-compiler:${Versions.AndroidX.androidArc}"
    const val RoomTesting =
        "androidx.room:room-testing:${Versions.AndroidX.androidArc}"

}