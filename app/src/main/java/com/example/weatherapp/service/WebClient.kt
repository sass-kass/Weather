package com.example.weatherapp.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT_MS: Long = 60
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

object WebClient {
    private var retrofit: Retrofit? = null

    fun getClient(): WeatherService {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create()
                    )
                )
                .client(
                    OkHttpClient.Builder().connectTimeout(
                        CONNECTION_TIMEOUT_MS,
                        TimeUnit.SECONDS
                    ).addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
                )
                .build()
        }
        return retrofit!!.create(WeatherService::class.java)
    }
}