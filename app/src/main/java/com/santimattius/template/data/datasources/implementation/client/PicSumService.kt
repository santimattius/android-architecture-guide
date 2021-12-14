package com.santimattius.template.data.datasources.implementation.client

import com.santimattius.template.data.models.NetworkPicture
import retrofit2.http.GET

interface PicSumService {

    @GET("/pictures")
    suspend fun fetchList(): List<NetworkPicture>
}