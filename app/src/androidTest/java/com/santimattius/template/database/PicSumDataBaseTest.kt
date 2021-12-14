package com.santimattius.template.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.santimattius.template.data.datasources.implementation.database.PicSumDao
import com.santimattius.template.data.datasources.implementation.database.PicSumDataBase
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PicSumDataBaseTest {

    private lateinit var picSumDao: PicSumDao
    private lateinit var db: PicSumDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PicSumDataBase::class.java
        ).build()
        picSumDao = db.picSumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePicturesAndReadInListOfPictures() {
        val generate = PictureEntityMother.generate(size = 2)
        picSumDao.insertPictures(generate)
        val result = picSumDao.getAll()
        Assert.assertThat(result, CoreMatchers.equalTo(generate))
    }


    @Test
    @Throws(Exception::class)
    fun writePicturesAndReadInListOfPicturesWithCountValidation() {
        val generate = PictureEntityMother.generate(size = 2)
        picSumDao.insertPictures(generate)
        val result = picSumDao.count()
        Assert.assertThat(result, CoreMatchers.equalTo(generate.size))
    }
}