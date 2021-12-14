package com.santimattius.template.data.models.mapping

import com.santimattius.template.data.models.PictureEntity
import com.santimattius.template.domain.entities.Picture

internal fun List<Picture>.asDbEntities() = this.map { it.asDbEntity() }

internal fun Picture.asDbEntity() = PictureEntity(
    author = author,
    width = width,
    downloadUrl = downloadUrl,
    id = id,
    url = url,
    height = height
)