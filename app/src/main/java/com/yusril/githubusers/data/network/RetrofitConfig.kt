package com.yusril.githubusers.data.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.yusril.githubusers.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private const val BASE_URL = "https://api.github.com/"
    private const val HEADER_AUTHORIZATION = "Authorization"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()

            val newUrl = request.url.newBuilder()
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .addHeader(HEADER_AUTHORIZATION, "Bearer ${BuildConfig.API_KEY}")
                .build()

            chain.proceed(newRequest)
        })
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .build()

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}