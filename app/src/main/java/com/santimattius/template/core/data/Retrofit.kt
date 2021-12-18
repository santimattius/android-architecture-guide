package com.santimattius.template.core.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val okHttpClient = OkHttpClient.Builder().build()

internal inline fun <reified S> service(baseUrl: String): S = create(baseUrl = baseUrl)
    .run { create(S::class.java) }

private fun create(baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()