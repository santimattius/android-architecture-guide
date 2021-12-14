package com.santimattius.template.data.datasources.implementation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santimattius.template.data.models.PictureEntity

@Database(entities = [PictureEntity::class], version = 1, exportSchema = false)
abstract class PicSumDataBase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "pic_sum_database"

        fun create(context: Context) =
            Room.databaseBuilder(context, PicSumDataBase::class.java, DATABASE_NAME)
                .build()
    }

    abstract fun picSumDao(): PicSumDao
}