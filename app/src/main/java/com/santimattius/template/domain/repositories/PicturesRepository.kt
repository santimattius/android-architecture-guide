package com.santimattius.template.domain.repositories

import com.santimattius.template.domain.entities.Picture

interface PicturesRepository {

    suspend fun getPictures(): List<Picture>
}