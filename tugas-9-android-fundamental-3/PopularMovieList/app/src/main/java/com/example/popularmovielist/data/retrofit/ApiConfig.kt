package com.example.popularmovielist.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun apiService(): ApiService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val loggingInterceptor = httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addInterceptor { chain ->
//                    val original = chain.request()
//                    val originalHttpUrl = original.url
//
//                    val url = originalHttpUrl.newBuilder()
//                        .addQueryParameter("api_key", "5a89cae6a766d9e4328f259850cbc0d7")
//                        .build()
//
//                    val requestBuilder = original.newBuilder().url(url)
//                    val request = requestBuilder.build()
//                    chain.proceed(request)
//                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}