package com.mm.android.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitConnection {

    companion object {
        private const val BASE_URL = "http://15.165.161.133:8080/"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()


                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE!!
        }

    }
}