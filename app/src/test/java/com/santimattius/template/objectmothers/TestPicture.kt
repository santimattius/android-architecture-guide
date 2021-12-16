package com.santimattius.template.objectmothers

import com.santimattius.template.domain.entities.Picture

data class TestPicture(
    override val author: String,
    override val width: Int,
    override val downloadUrl: String,
    override val id: String,
    override val url: String,
    override val height: Int
) : Picture