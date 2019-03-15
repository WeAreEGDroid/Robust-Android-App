package com.ahmedadel.robustandroid.datalayer.remote.di

import com.ahmedadel.robustandroid.datalayer.remote.ApiKeyInterceptor
import com.ahmedadel.robustandroid.datalayer.remote.ApiService
import com.ahmedadel.robustandroid.datalayer.remote.BuildConfig
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created at Tito on 3/15/19
 *
 * Dagger Module that provides Remote stuff like Retrofit, ApiKeyInterceptor and OkLog.
 */

@Module
class RemoteModule {

    @Provides
    @RemoteScope
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(apiKeyInterceptor)
        builder.addInterceptor(httpLoggingInterceptor)
            .connectTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .readTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
        return builder.build()
    }

    @Provides
    @RemoteScope
    internal fun provideApiKeyInterceptor() = ApiKeyInterceptor()

    @Provides
    @RemoteScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        return logging
    }

    @Provides
    @RemoteScope
    fun provideOkLogInterceptor(): Interceptor {
        return OkLogInterceptor.builder().build()
    }

    @Provides
    @RemoteScope
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    @RemoteScope
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}