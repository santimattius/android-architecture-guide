package com.santimattius.template.data.datasources.implementation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santimattius.template.data.models.PictureEntity

@Dao
interface PicSumDao {

    @Query("SELECT * FROM picture")
    suspend fun getAll(): List<PictureEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPictures(pictures: List<PictureEntity>)

    @Query("SELECT COUNT(id) FROM picture")
    suspend fun count(): Int
}
