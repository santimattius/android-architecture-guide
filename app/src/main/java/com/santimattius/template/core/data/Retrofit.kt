package com.santimattius.template.core.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val okHttpClient = HttpLoggingInterceptor().run {
    level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder().addInterceptor(this).build()
}

internal inline fun <reified S> service(baseUrl: String): S = create(baseUrl = baseUrl)
    .run { create(S::class.java) }

private fun create(baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()