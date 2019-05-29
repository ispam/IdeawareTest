package com.example.ideawaretest.DI

import android.content.Context
import android.util.Log
import com.example.ideawaretest.BuildConfig
import com.example.ideawaretest.Data.Remote.APIService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module(includes = arrayOf(AppModule::class))
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://storage.googleapis.com/"
    }

    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, 10 * 1024 * 1024)

    @Singleton
    @Provides
    fun provideFile(context: Context): File = File(context.cacheDir, "okhttp_cache")

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                message -> Log.v("LoggingInterceptor", message)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache, context: Context): OkHttpClient {
        return if (BuildConfig.DEBUG){
            OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .cache(cache)
                .build()
        }
    }
}