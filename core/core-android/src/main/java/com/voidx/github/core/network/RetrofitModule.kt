package com.voidx.github.core.network

import com.voidx.github.core.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal interface RetrofitModule {

    companion object {

        @[Provides Singleton]
        fun providesOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient().newBuilder()

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
                )
            }

            return builder.build()
        }

        @[Provides Singleton]
        fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }

}