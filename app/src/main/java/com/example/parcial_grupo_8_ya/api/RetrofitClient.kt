package com.example.parcial_grupo_8_ya.api

import com.example.parcial_grupo_8_ya.utils.Constants.BASE_URL
import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitClient {
        private val AUTH = "Basic " + Base64.encodeToString("johnd:m38rmF$".toByteArray(), Base64.NO_WRAP)
        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization",AUTH)
                    .method(original.method, original.body)

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()


        val instance: UsersApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            retrofit.create(UsersApi::class.java)
        }

    }
