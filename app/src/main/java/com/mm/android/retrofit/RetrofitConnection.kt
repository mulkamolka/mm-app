package com.mm.android.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitConnection {

    companion object {
        private const val BASE_URL_BACK = "http://34.125.36.102:8080/"
        private const val BASE_URL_DATA = "http://34.125.36.102:8000/"
        private var INSTANCE_BACK: Retrofit? = null
        private var INSTANCE_DATA: Retrofit? = null


        fun getInstanceBack(): Retrofit {
            if (INSTANCE_BACK == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()


                INSTANCE_BACK = Retrofit.Builder()
                    .baseUrl(BASE_URL_BACK)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE_BACK!!
        }

        fun getInstanceData(): Retrofit {
            if (INSTANCE_DATA == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()


                INSTANCE_DATA = Retrofit.Builder()
                    .baseUrl(BASE_URL_DATA)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE_DATA!!
        }

    }
}