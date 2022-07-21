package com.voidx.core.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.voidx.core.data.Environment
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
        fun providesGson(): Gson {
            return GsonBuilder().setLenient().create()
        }

        @[Provides Singleton]
        fun providesRetrofit(
            okHttpClient: OkHttpClient,
            environment: Environment,
            gson: Gson
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(environment.url)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }

}
