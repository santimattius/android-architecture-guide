package com.santimattius.template.data.datasources.implementation.database

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.santimattius.template.MainApplication
import com.santimattius.template.objectmothers.PictureEntityMother
import com.santimattius.template.utils.KoinRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.annotation.Config
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.R], application = MainApplication::class)
class PicSumDataBaseTest : KoinTest {

    @get:Rule
    val koinRule = KoinRule.androidx()

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
    fun writePicturesAndReadInListOfPictures() = runBlocking {
        val generate = PictureEntityMother.generate(size = 2)
        picSumDao.insertPictures(generate)
        val result = picSumDao.getAll()
        assertThat(result, IsEqual(generate))
    }

    @Test
    @Throws(Exception::class)
    fun writePicturesAndReadInListOfPicturesWithCountValidation() = runBlocking {
        val generate = PictureEntityMother.generate(size = 2)
        picSumDao.insertPictures(generate)
        val result = picSumDao.count()
        assertThat(result, IsEqual(generate.size))
    }
}
