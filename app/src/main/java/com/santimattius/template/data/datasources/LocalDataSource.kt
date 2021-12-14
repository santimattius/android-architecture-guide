package com.santimattius.template.data.datasources

import com.santimattius.template.domain.entities.Picture

interface LocalDataSource {

    suspend fun isEmpty(): Boolean

    suspend fun getPictures(): List<Picture>

    suspend fun insertPictures(pictures: List<Picture>)
}