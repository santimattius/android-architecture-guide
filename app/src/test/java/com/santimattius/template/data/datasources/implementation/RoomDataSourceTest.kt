package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.datasources.implementation.database.PicSumDao
import com.santimattius.template.domain.entities.Picture
import com.santimattius.template.utils.CoroutinesTestRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RoomDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var roomDataSource: RoomDataSource
    private lateinit var picSumDao: PicSumDao


    @Before
    fun setUp() {
        picSumDao = mockk()
        roomDataSource = RoomDataSource(picSumDao, coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `validate is empty check`() = runBlockingTest {
        //Given
        every { picSumDao.count() } returns 0
        // When
        val isEmpty = roomDataSource.isEmpty()
        // Then
        assert(isEmpty)
        every { picSumDao.count() }
    }

    @Test
    fun `validate no is empty check`() = runBlockingTest {
        //Given
        every { picSumDao.count() } returns 10
        // When
        val isEmpty = roomDataSource.isEmpty()
        // Then
        Assert.assertThat(isEmpty, IsEqual(false))
        every { picSumDao.count() }
    }

    @Test
    fun getPictures() = runBlockingTest {
        //Given
        every { picSumDao.getAll() } returns emptyList()
        //When
        val pictures = roomDataSource.getPictures()
        //Then
        assert(pictures.isEmpty())
        every { picSumDao.getAll() }
    }

    @Test
    fun insertPictures() = runBlockingTest {
        val pictures = emptyList<Picture>()
        every { picSumDao.insertPictures(emptyList()) } returns Unit
        roomDataSource.insertPictures(pictures)
        verify { picSumDao.insertPictures(emptyList()) }
    }
}