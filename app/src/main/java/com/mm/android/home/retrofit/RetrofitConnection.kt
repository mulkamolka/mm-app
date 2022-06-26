package com.mm.android.home.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {

    companion object {
        private const val BASE_URL= "url"
        private var INSTANCE: Retrofit? = null
    }

    fun getInstance(): Retrofit {
        if (INSTANCE == null) {
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return  INSTANCE!!
    }
}