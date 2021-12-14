package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.implementation.database.PicSumDao
import com.santimattius.template.data.models.mapping.asDbEntities
import com.santimattius.template.domain.entities.Picture
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class RoomDataSource(
    private val picSumDao: PicSumDao,
    private val dispatcher: CoroutineContext
) : LocalDataSource {

    override suspend fun isEmpty() = withContext(dispatcher) {
        picSumDao.count() <= 0
    }

    override suspend fun getPictures() = withContext(dispatcher) {
        picSumDao.getAll()
    }

    override suspend fun insertPictures(pictures: List<Picture>) = withContext(dispatcher) {
        picSumDao.insertPictures(pictures.asDbEntities())
    }
}


