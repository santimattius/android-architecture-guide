package com.santimattius.template.data.datasources

import com.santimattius.template.domain.entities.Picture

interface RemoteDataSource {

    suspend fun getPictures(): List<Picture>
}