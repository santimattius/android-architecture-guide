package com.santimattius.template.domain.usecases

import com.santimattius.template.domain.repositories.PicturesRepository

class GetPictures(private val repository: PicturesRepository) {

    suspend operator fun invoke() = repository.getPictures()
}