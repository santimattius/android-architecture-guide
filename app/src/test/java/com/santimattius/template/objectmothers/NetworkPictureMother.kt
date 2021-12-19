package com.santimattius.template.objectmothers

import com.santimattius.template.data.models.NetworkPicture

object NetworkPictureMother {

    fun pictures() = (1..10).map { NetworkPicture(id = it.toString()) }
}