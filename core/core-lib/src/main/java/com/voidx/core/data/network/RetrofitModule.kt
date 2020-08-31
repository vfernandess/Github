package com.voidx.core.data.network

import com.voidx.core.data.Environment
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
        fun providesOkHttpClient(environment: Environment): OkHttpClient {
            val builder = OkHttpClient().newBuilder()

            if (environment.debug) {
                builder.addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                        .setLevel(HttpLoggingInterceptor.Level.BASIC)
                )
            }

            return builder.build()
        }

        @[Provides Singleton]
        fun providesRetrofit(okHttpClient: OkHttpClient, environment: Environment): Retrofit {
            return Retrofit.Builder()
                .baseUrl(environment.url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }

}