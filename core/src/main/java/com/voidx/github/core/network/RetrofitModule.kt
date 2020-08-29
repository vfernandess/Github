package com.voidx.github.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {

    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()

//        if (BuildConfig.DEBUG) {
//            builder.addInterceptor(
//                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
//                    .setLevel(HttpLoggingInterceptor.Level.BODY)
//            )
//        }
        return builder
            .build()
    }

    fun providesRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

}